package com.daily.tool;
/*
 * Copyright (c) 2019, com.lanqiao.org
 *
 * All rights reserved.
 */

/**
 * 反射对象的字段名和类型.
 *
 * @author Administrator
 * @version 1.0.0.0, 2019/7/2 0002
 */
public class FieldAndType {
    private String fieldName;
    private String fieldType;

    public FieldAndType() {
    }

    public FieldAndType(String fieldName, String fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    /**
     * Gets the value of fieldName.
     *
     * @return the value of fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the fieldName.
     * <p>
     * <p>You can use getFieldName() to get the value of fieldName</p>
     *
     * @param fieldName fieldName
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Gets the value of fieldType.
     *
     * @return the value of fieldType
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * Sets the fieldType.
     * <p>
     * <p>You can use getFieldType() to get the value of fieldType</p>
     *
     * @param fieldType fieldType
     */
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
