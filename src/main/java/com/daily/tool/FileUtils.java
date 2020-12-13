package com.daily.tool;

import java.io.*;

public class FileUtils {
    public static byte[] readFileToByteArray(File file) {
        try {
            FileInputStream fis=new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int len = -1;
            while((len=fis.read(temp))!=-1){
                bos.write(temp, 0, len);
            }
            return bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static byte[] readFileToByteArray(String filePath) {
        File file = new File(filePath);
        if (!file.exists()){
            return null;
        }
        try {
            FileInputStream fis=new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int len = -1;
            while((len=fis.read(temp))!=-1){
                bos.write(temp, 0, len);
            }
            return bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
