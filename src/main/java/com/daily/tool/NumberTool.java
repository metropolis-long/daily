package com.daily.tool;

import java.util.Random;

/**
 * @ClassName NuumberTool
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/27 23:42
 * @VERSION 1.0.0
 */
public class NumberTool {
    /**
     * generate random string.
     * @param length random string length
     * @return random string
     */
    public static String getStrRamon(int length) {
        Random random = new Random();
        int nextInt =random.nextInt(6);
        String sources = "0123456789QWERTYUIOPLKJHGHFDDSAZXCVBNM"; // 加上一些字母，就可以生成pc站的验证码了
        StringBuffer flag = new StringBuffer();
        for (int j = nextInt; j < nextInt+length; j++) {
            flag.append(sources.charAt(random.nextInt(38)) + "");
        }
        String str = flag.toString();
        System.out.println(str);
        return str;
    }
}
