package com.daily.dao.auto;

import com.daily.pojo.CitiesProvince;
import com.daily.pojo.CitiesProvinceExample;
import java.util.List;

public interface CitiesProvinceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cities_province
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long provinceCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cities_province
     *
     * @mbggenerated
     */
    int insert(CitiesProvince record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cities_province
     *
     * @mbggenerated
     */
    int insertSelective(CitiesProvince record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cities_province
     *
     * @mbggenerated
     */
    List<CitiesProvince> selectByExample(CitiesProvinceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cities_province
     *
     * @mbggenerated
     */
    CitiesProvince selectByPrimaryKey(Long provinceCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cities_province
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CitiesProvince record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cities_province
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CitiesProvince record);
}