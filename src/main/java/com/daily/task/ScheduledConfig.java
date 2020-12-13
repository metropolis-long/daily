package com.daily.task;

import com.daily.dao.auto.SpringScheduledTaskMapper;
import com.daily.dao.my.SpringScheduledTaskDao;
import com.daily.pojo.SpringScheduledTask;
import com.daily.service.ICacheService;
import com.daily.service.SpringScheduledTaskService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.Assert;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @ClassName ScheduledConfig
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/11/2 19:40
 * @VERSION 1.0.0
 */
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {
    @Autowired
    private ApplicationContext context;
    @Autowired
//    private ICacheService cacheService;
    private SpringScheduledTaskDao cacheService;
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        System.out.println("insert task to spring  999999999999");
        for (SpringScheduledTask springScheduledCron : cacheService.findAll()) {
            Class<?> clazz;
            Object task;
            try {
//                System.out.println("insert task to spring  1111");
                clazz = Class.forName(springScheduledCron.getCronKey());
                task = context.getBean(clazz);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("spring_scheduled_task表数据" + springScheduledCron.getCronKey() + "有误", e);
            } catch (BeansException e) {
                throw new IllegalArgumentException(springScheduledCron.getCronKey() + "未纳入到spring管理", e);
            }
            Assert.isAssignable(ScheduledTask.class, task.getClass(), "定时任务类必须实现ScheduledTask接口");
            // 可以通过改变数据库数据进而实现动态改变执行周期
            taskRegistrar.addTriggerTask(((Runnable) task),
                    triggerContext -> {
//                        System.out.println("insert task to spring  2222222222");
                        String cronExpression = cacheService.findByCronKey(springScheduledCron.getCronKey()).getCronExpression();
                        return new CronTrigger(cronExpression).nextExecutionTime(triggerContext);
                    }
            );
        }
    }
    @Bean
    public Executor taskExecutor() {
//        System.out.println("insert task to spring  0000000000000000");
        return Executors.newScheduledThreadPool(10);
    }
}