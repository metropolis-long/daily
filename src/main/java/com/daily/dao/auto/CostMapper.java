package com.daily.dao.auto;

import com.daily.pojo.Cost;

public interface CostMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long costId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost
     *
     * @mbggenerated
     */
    int insert(Cost record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost
     *
     * @mbggenerated
     */
    int insertSelective(Cost record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost
     *
     * @mbggenerated
     */
    Cost selectByPrimaryKey(Long costId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Cost record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Cost record);
}