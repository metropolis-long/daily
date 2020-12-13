package com.daily.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @ClassName MyConfig
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/18 19:15
 * @VERSION 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "application")
public class MyConfig{

    /**
     * 版本
     */
    private String version;
    /**
     * 上传文件路径
     */
    private static String profile;

    private static String domain;

    public static String getDomain() {
        return domain;
    }

    public static void setDomain(String domain) {
        MyConfig.domain = domain;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


}