package com.daily.dao.my;

import com.daily.pojo.SpringScheduledTask;

import java.util.List;

/**
 * @ClassName SpringScheduledTask
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/11/2 19:48
 * @VERSION 1.0.0
 */
public interface SpringScheduledTaskDao {
    List<SpringScheduledTask> findAll();

    SpringScheduledTask findByCronKey(String cronKey);
}
