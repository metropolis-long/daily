package com.daily.springbootmybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {
    @Test
    public void Test1(){
        String version = SpringVersion.getVersion();
        String version1 = SpringBootVersion.getVersion();
        System.out.println(version+"==============="+version1);
    }
    @Test
    public void zeroTest() {
        System.out.println("SpringbootMybatisApplicationTests default-test");
    }
}
