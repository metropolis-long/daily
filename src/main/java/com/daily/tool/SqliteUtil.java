package com.daily.tool;
/*
 * Copyright (c) 2019, com.lanqiao.org
 *
 * All rights reserved.
 */

import org.springframework.beans.BeanUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * .
 *
 * @author Administrator
 * @version 1.0.0.0, 2019/7/1 0001
 */
public class SqliteUtil<T> {

    private static final String Class_Name = "org.sqlite.JDBC";
    private static final String DB_TYPE = "jdbc:sqlite:";
    private static final String DB_PATH = "D:\\work\\sqlite\\sqlite322.db";
    private static final String DB_URL = DB_TYPE + DB_PATH;
    private static final List<String> insertFieldList = new Vector<>();
    private static final List<String> seleftFieldList = new Vector<>();

    public static void main(String args[]) throws SQLException {
        final DecimalFormat df=new DecimalFormat("0.00");//设置保留位数


            System.out.println(df.format((float)50/102));
            System.out.println(df.format((float)50/102 +50));

    }

    public void excuteDelete(Class<T> clazz, final String dbPath) {
        if (!checkOrCreateDatabaseFile(dbPath)) {
            return;
        }
        Connection connection = createConnection(dbPath);
        String tableName = getTableNameFromClass(clazz);
        try {
            Statement statement = connection.createStatement();
            String sql = "delete from " + tableName;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    /**
     * 根据类查询sqlite表全部数据.
     * 并且返回类列表
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> executeQuery(Class<T> clazz, final String dbPath) {
        if (!checkOrCreateDatabaseFile(dbPath)) {
            return null;
        }
        Connection connection = createConnection(dbPath);
        initFieldList(clazz);
        String tableName = getTableNameFromClass(clazz);
        List<T> list = new ArrayList();
        String sql = "SELECT ";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            for (int i = 0; i < seleftFieldList.size(); i++) {
                if (i == seleftFieldList.size() - 1) {
                    sql += insertFieldList.get(i) + " as " + seleftFieldList.get(i);
                    break;
                }
                sql += insertFieldList.get(i) + " as " + seleftFieldList.get(i) + ",";
            }
            sql += " FROM " + tableName;

            // 执行查询语句
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                HashMap<String, Object> source = new HashMap<>();
                for (String f : seleftFieldList) {
                    String value = rs.getString(f);
                    source.put(f, value);
                }
                // 调用无参构造器 ，若是没有，则会报异常
                Object target = clazz.newInstance();
                BeanUtils.copyProperties(source, target);
                list.add((T) target);
            }
        } catch (InstantiationException e) {
            System.out.println("调用无参构造器 ，若是没有，则会报异常");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return list;
    }

    /**
     * 根据list及T的具体类型.
     * 插入类对应的sqlite表
     *
     * @param list
     * @param dbPath
     */
    public void executeInsert(List<T> list, final String dbPath) {
        if (list == null || list.size() == 0) {
            System.err.println("列表为空，返回退出。");
            return;
        }
        if (!checkOrCreateDatabaseFile(dbPath)) {
            return;
        }

        Class clazz = list.get(0).getClass();
        //删除原表所有数据
//        excuteDelete(clazz,dbPath);
        Connection connection = createConnection(dbPath);
        initFieldList(clazz);
        String tableName = getTableNameFromClass(clazz);

        String sqlReplaceParam = "";
        String sql = "INSERT INTO " + tableName + "(";
        for (int i = 0; i < insertFieldList.size(); i++) {
            if (i == insertFieldList.size() - 1) {
                sql += insertFieldList.get(i);
                sqlReplaceParam += "?";
                break;
            }
            sql += insertFieldList.get(i) + ",";
            sqlReplaceParam += "?,";
        }
        sql += ") VALUES(" + sqlReplaceParam + ")";
        ;
        Statement statement = null;
        PreparedStatement pstmt = null;    // 数据库操作
        try {
            statement = connection.createStatement();
            pstmt = connection.prepareStatement(sql);
            statement.setQueryTimeout(60 * 5); // set timeout to  5 minute.
            connection.setAutoCommit(false);
            for (T t : list) {
                for (int j = 0; j < seleftFieldList.size(); j++) {
                    Object object = tryGetField(t, seleftFieldList.get(j));
                    if (object == null) {
                        pstmt.setString(j + 1, "");
                    } else {
                        pstmt.setString(j + 1, object.toString());
                    }
                }
                pstmt.executeUpdate();
            }
            pstmt.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    private Object tryGetField(T t, String s) {
        return null;
    }


    public <T> String getTableNameFromClass(Class<T> clazz) {
        // 获取类注解
        String typeName = null;
        TableName myClassAnnotation = clazz.getAnnotation(TableName.class);
        if (myClassAnnotation != null) {
            typeName = myClassAnnotation.value();
        } else {
            String[] allName = clazz.getName().split("\\.");
            if (allName.length > 1) {
                typeName = allName[allName.length - 1];
            } else {
                typeName = clazz.getName();
            }
        }
        System.out.println(typeName);
        return typeName;
    }


    public synchronized <T> T initFieldList(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        //静态变量，清空内容
        seleftFieldList.clear();
        insertFieldList.clear();
        for (Field field : fields) {
            //获取注解字段
            TableField myField = field.getAnnotation(TableField.class);
            if (!NullUtil.isNull(myField)) {
                seleftFieldList.add(field.getName());
                if (myField.value() != "") {
                    insertFieldList.add(myField.value());
                } else {
                    insertFieldList.add(field.getName());
                }

            }
        }
        return null;
    }

    /**
     * 检查数据库文件是否存在.
     *
     * @param databaseFilePath dbURL
     * @return
     */
    public static boolean checkOrCreateDatabaseFile(final String databaseFilePath) {
        if (!checkExist(databaseFilePath)) {
            try {
                System.out.println("数据库不存在.");
            } catch (final Exception e) {
                e.printStackTrace();
            }
            return false;
        } else {
            return true;
        }

    }

    private static boolean checkExist(String databaseFilePath) {
        File file = new File(databaseFilePath);
        if (file.exists()){
            return true;
        }
        return false;
    }

    // 创建Sqlite数据库连接
    public static Connection createConnection(final String dbPath) {
        try {
            Class.forName(Class_Name);
            return DriverManager.getConnection(DB_TYPE + dbPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 关闭连接
     *
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null)
                connection.close();
                if (connection.isClosed()){
                    connection=null;
                    System.gc();
                }else {
                    connection.close();
                }
            System.err.println("connection close.");
        } catch (SQLException e) {
            // connection close failed.
            System.err.println("connection close failed.");
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
