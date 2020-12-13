package com.daily.search;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName EventSearch
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/18 23:00
 * @VERSION 1.0.0
 */
public class EventSearch extends SearchDTO {
    private BigDecimal timeCost;

    private Integer remind;
    private String planTime;
    private Date remindTime;

    public Integer getRemind() {
        return remind;
    }

    public void setRemind(Integer remind) {
        this.remind = remind;
    }

    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public BigDecimal getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(BigDecimal timeCost) {
        this.timeCost = timeCost;
    }
}
