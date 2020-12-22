package com.daily.search;

import java.math.BigDecimal;

/**
 * @ClassName TravelSearch
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/31 15:21
 * @VERSION 1.0.0
 */
public class TravelSearch extends SearchDTO{
    private BigDecimal south;
    private BigDecimal north;
    private BigDecimal west;
    private BigDecimal east;
    private Long travelId;
    private Long provinceCode;
    private Long cityCode;

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    public Long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Long getTravelId() {
        return travelId;
    }

    public void setTravelId(Long travelId) {
        this.travelId = travelId;
    }

    public BigDecimal getSouth() {
        return south;
    }

    public void setSouth(BigDecimal south) {
        this.south = south;
    }

    public BigDecimal getNorth() {
        return north;
    }

    public void setNorth(BigDecimal north) {
        this.north = north;
    }

    public BigDecimal getWest() {
        return west;
    }

    public void setWest(BigDecimal west) {
        this.west = west;
    }

    public BigDecimal getEast() {
        return east;
    }

    public void setEast(BigDecimal east) {
        this.east = east;
    }
}
