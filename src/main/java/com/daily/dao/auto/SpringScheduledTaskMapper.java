package com.daily.dao.auto;

import com.daily.pojo.SpringScheduledTask;
import com.daily.pojo.SpringScheduledTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpringScheduledTaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_scheduled_task
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer cronId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_scheduled_task
     *
     * @mbggenerated
     */
    int insert(SpringScheduledTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_scheduled_task
     *
     * @mbggenerated
     */
    int insertSelective(SpringScheduledTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_scheduled_task
     *
     * @mbggenerated
     */
    List<SpringScheduledTask> selectByExample(SpringScheduledTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_scheduled_task
     *
     * @mbggenerated
     */
    SpringScheduledTask selectByPrimaryKey(Integer cronId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_scheduled_task
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SpringScheduledTask record, @Param("example") SpringScheduledTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_scheduled_task
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SpringScheduledTask record, @Param("example") SpringScheduledTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_scheduled_task
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SpringScheduledTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_scheduled_task
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SpringScheduledTask record);
}