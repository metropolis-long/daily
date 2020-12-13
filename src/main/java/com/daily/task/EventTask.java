package com.daily.task;

import com.daily.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName EventTask
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/11/2 19:26
 * @VERSION 1.0.0
 */

@Component
public class EventTask implements ScheduledTask{

    @Autowired
    private EventService eventService;
    private int i;
    @Override
    public void execute() {
//        System.out.println("i am executed...............");
        eventService.remindMeDoEvents();
    }

}
