package com.daily.service;

import com.daily.dao.my.SpringScheduledTaskDao;
import com.daily.pojo.SpringScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SpringScheduledTaskService
 * @Description TODO
 * @Author xiaozhuyelong
 * @Date 2020/11/2 22:56
 * @VERSION 1.0.0
 */
@Service(value = "springScheduledTaskService")
public class SpringScheduledTaskService {

    @Autowired
    private SpringScheduledTaskDao springScheduledTaskDao;
    public List<SpringScheduledTask> findAll(){
        return springScheduledTaskDao.findAll();
    }

    public SpringScheduledTask findByCronKey(String cronKey){
        return springScheduledTaskDao.findByCronKey(cronKey);
    }

    public void updateCronExpressionByCronKey(String newCron, String cronKey) {
    }

    public void updateStatusByCronKey(Integer status, String cronKey) {
    }
}
