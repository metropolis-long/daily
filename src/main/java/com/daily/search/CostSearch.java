package com.daily.search;

public class CostSearch extends SearchDTO {

    private Long costId;
    private String thisYear;

    public String getThisYear() {
        return thisYear;
    }

    public void setThisYear(String thisYear) {
        this.thisYear = thisYear;
    }

    /**
     * 按周，月，年统计消费
     */
    private Integer timeType = 1;

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public Long getCostId() {
        return costId;
    }

    public void setCostId(Long costId) {
        this.costId = costId;
    }
}
