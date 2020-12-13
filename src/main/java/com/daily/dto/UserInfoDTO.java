package com.daily.dto;

import com.daily.pojo.UserInfo;

/**
 * @ClassName UserInfoDTO
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/27 23:34
 * @VERSION 1.0.0
 */
public class UserInfoDTO extends UserInfo {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
