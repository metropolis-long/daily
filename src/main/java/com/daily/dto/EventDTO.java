package com.daily.dto;

import com.daily.pojo.Event;

import java.math.BigDecimal;

/**
 * @ClassName EventDTO
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/18 19:15
 * @VERSION 1.0.0
 */
public class EventDTO extends Event {
    private String email;

    private String avatar;
    private String nickName;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEventCostStr(String eventCost){
        super.setTimeCost(new BigDecimal(eventCost));
    }

}
