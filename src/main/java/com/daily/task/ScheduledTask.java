package com.daily.task;

import com.daily.dao.my.SpringScheduledTaskDao;
import com.daily.msg.ResultCodeMsg;
import com.daily.pojo.SpringScheduledTask;
import com.daily.tool.SpringUtils;


/**
 * @author metropolis-long
 * @date 2019/5/11
 */
public interface ScheduledTask extends Runnable {
    /**
     * 定时任务方法
     */
    void execute();
    /**
     * 实现控制定时任务启用或禁用的功能
     */
    @Override
    default void run() {
//        System.out.println("insert task to spring  4444444444");
        SpringScheduledTaskDao repository = SpringUtils.getBean(SpringScheduledTaskDao.class);
        SpringScheduledTask scheduledCron = repository.findByCronKey(this.getClass().getName());
        if (ResultCodeMsg.DISABLED.getCode().equals(scheduledCron.getStatusId())) {
            // 任务是禁用状态
            return;
        }
        execute();
    }
}
