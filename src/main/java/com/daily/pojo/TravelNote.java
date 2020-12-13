package com.daily.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TravelNote implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_note.travel_id
     *
     * @mbggenerated
     */
    private Long travelId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_note.travel_title
     *
     * @mbggenerated
     */
    private String travelTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_note.travel_img
     *
     * @mbggenerated
     */
    private Long travelImg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_note.user_id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_note.province_code
     *
     * @mbggenerated
     */
    private Long provinceCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_note.city_code
     *
     * @mbggenerated
     */
    private Long cityCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_note.longitude
     *
     * @mbggenerated
     */
    private BigDecimal longitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_note.latitude
     *
     * @mbggenerated
     */
    private BigDecimal latitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_note.tag
     *
     * @mbggenerated
     */
    private String tag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_note.updated
     *
     * @mbggenerated
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column travel_note.created
     *
     * @mbggenerated
     */
    private Date created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table travel_note
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_note.travel_id
     *
     * @return the value of travel_note.travel_id
     *
     * @mbggenerated
     */
    public Long getTravelId() {
        return travelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_note.travel_id
     *
     * @param travelId the value for travel_note.travel_id
     *
     * @mbggenerated
     */
    public void setTravelId(Long travelId) {
        this.travelId = travelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_note.travel_title
     *
     * @return the value of travel_note.travel_title
     *
     * @mbggenerated
     */
    public String getTravelTitle() {
        return travelTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_note.travel_title
     *
     * @param travelTitle the value for travel_note.travel_title
     *
     * @mbggenerated
     */
    public void setTravelTitle(String travelTitle) {
        this.travelTitle = travelTitle == null ? null : travelTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_note.travel_img
     *
     * @return the value of travel_note.travel_img
     *
     * @mbggenerated
     */
    public Long getTravelImg() {
        return travelImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_note.travel_img
     *
     * @param travelImg the value for travel_note.travel_img
     *
     * @mbggenerated
     */
    public void setTravelImg(Long travelImg) {
        this.travelImg = travelImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_note.user_id
     *
     * @return the value of travel_note.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_note.user_id
     *
     * @param userId the value for travel_note.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_note.province_code
     *
     * @return the value of travel_note.province_code
     *
     * @mbggenerated
     */
    public Long getProvinceCode() {
        return provinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_note.province_code
     *
     * @param provinceCode the value for travel_note.province_code
     *
     * @mbggenerated
     */
    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_note.city_code
     *
     * @return the value of travel_note.city_code
     *
     * @mbggenerated
     */
    public Long getCityCode() {
        return cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_note.city_code
     *
     * @param cityCode the value for travel_note.city_code
     *
     * @mbggenerated
     */
    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_note.longitude
     *
     * @return the value of travel_note.longitude
     *
     * @mbggenerated
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_note.longitude
     *
     * @param longitude the value for travel_note.longitude
     *
     * @mbggenerated
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_note.latitude
     *
     * @return the value of travel_note.latitude
     *
     * @mbggenerated
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_note.latitude
     *
     * @param latitude the value for travel_note.latitude
     *
     * @mbggenerated
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_note.tag
     *
     * @return the value of travel_note.tag
     *
     * @mbggenerated
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_note.tag
     *
     * @param tag the value for travel_note.tag
     *
     * @mbggenerated
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_note.updated
     *
     * @return the value of travel_note.updated
     *
     * @mbggenerated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_note.updated
     *
     * @param updated the value for travel_note.updated
     *
     * @mbggenerated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column travel_note.created
     *
     * @return the value of travel_note.created
     *
     * @mbggenerated
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column travel_note.created
     *
     * @param created the value for travel_note.created
     *
     * @mbggenerated
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}