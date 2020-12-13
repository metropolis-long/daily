package com.daily.search;

import java.util.List;

/**
 * @ClassName TravelSearch
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/31 15:21
 * @VERSION 1.0.0
 */
public class TravelSearch extends SearchDTO{
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
}
