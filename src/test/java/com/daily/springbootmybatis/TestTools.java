package com.daily.springbootmybatis;

import com.daily.tool.NumberTool;
import org.junit.Test;

/**
 * @ClassName TestTools
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/24 0:32
 * @VERSION 1.0.0
 */
public class TestTools {

    @Test
    public void testWords(){
        System.out.println("2Add".equalsIgnoreCase("2aDD"));
        for (int i = 0; i <2 ; i++) {
            NumberTool.getStrRamon(6);
        }

//        String p = "C:\\Users\\Administrator\\Desktop\\个人管理需求.docx";
//        ResultBody<String> r = WordConvertTools.wordToText(p);
//        System.out.println(r.getData());
    }
}
