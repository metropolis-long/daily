package com.daily.dao.my;

import com.daily.dto.EventDTO;
import com.daily.search.EventSearch;

import java.util.List;

/**
 * @ClassName EventDao
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/18 22:58
 * @VERSION 1.0.0
 */
public interface EventDao {
    List<EventDTO> eventList(EventSearch search);
    List<EventDTO> eventRemindList(EventSearch search);
    int count4EventList(EventSearch search);
}
