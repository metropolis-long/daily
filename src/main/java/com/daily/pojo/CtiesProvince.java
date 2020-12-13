package com.daily.pojo;

import java.io.Serializable;
import java.util.Date;

public class CtiesProvince implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cities_province.cities_province_code
     *
     * @mbggenerated
     */
    private Long citiesProvinceCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cities_province.cities_province_name
     *
     * @mbggenerated
     */
    private String citiesProvinceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cities_province.cities_province_level
     *
     * @mbggenerated
     */
    private String citiesProvinceLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cities_province.data_version
     *
     * @mbggenerated
     */
    private Integer dataVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cities_province.last_modified_time
     *
     * @mbggenerated
     */
    private Date lastModifiedTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cities_province.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cities_province
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cities_province.cities_province_code
     *
     * @return the value of cities_province.cities_province_code
     *
     * @mbggenerated
     */
    public Long getCitiesProvinceCode() {
        return citiesProvinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cities_province.cities_province_code
     *
     * @param citiesProvinceCode the value for cities_province.cities_province_code
     *
     * @mbggenerated
     */
    public void setCitiesProvinceCode(Long citiesProvinceCode) {
        this.citiesProvinceCode = citiesProvinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cities_province.cities_province_name
     *
     * @return the value of cities_province.cities_province_name
     *
     * @mbggenerated
     */
    public String getCitiesProvinceName() {
        return citiesProvinceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cities_province.cities_province_name
     *
     * @param citiesProvinceName the value for cities_province.cities_province_name
     *
     * @mbggenerated
     */
    public void setCitiesProvinceName(String citiesProvinceName) {
        this.citiesProvinceName = citiesProvinceName == null ? null : citiesProvinceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cities_province.cities_province_level
     *
     * @return the value of cities_province.cities_province_level
     *
     * @mbggenerated
     */
    public String getCitiesProvinceLevel() {
        return citiesProvinceLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cities_province.cities_province_level
     *
     * @param citiesProvinceLevel the value for cities_province.cities_province_level
     *
     * @mbggenerated
     */
    public void setCitiesProvinceLevel(String citiesProvinceLevel) {
        this.citiesProvinceLevel = citiesProvinceLevel == null ? null : citiesProvinceLevel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cities_province.data_version
     *
     * @return the value of cities_province.data_version
     *
     * @mbggenerated
     */
    public Integer getDataVersion() {
        return dataVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cities_province.data_version
     *
     * @param dataVersion the value for cities_province.data_version
     *
     * @mbggenerated
     */
    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cities_province.last_modified_time
     *
     * @return the value of cities_province.last_modified_time
     *
     * @mbggenerated
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cities_province.last_modified_time
     *
     * @param lastModifiedTime the value for cities_province.last_modified_time
     *
     * @mbggenerated
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cities_province.create_time
     *
     * @return the value of cities_province.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cities_province.create_time
     *
     * @param createTime the value for cities_province.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}