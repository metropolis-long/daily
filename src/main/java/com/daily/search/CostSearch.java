package com.daily.search;

public class CostSearch extends SearchDTO {

    private Long costId;

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
