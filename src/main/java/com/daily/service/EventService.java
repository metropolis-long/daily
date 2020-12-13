package com.daily.service;

import com.daily.dao.auto.EventMapper;
import com.daily.dao.my.EventDao;
import com.daily.dto.EventDTO;
import com.daily.msg.ResultBody;
import com.daily.search.EventSearch;
import com.daily.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName EventService
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/18 19:18
 * @VERSION 1.0.0
 */
@Service(value = "event")
public class EventService extends BaseService {
    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private MailService mailService;
    @Autowired
    private EventDao eventDao;

    public ResultBody<List<EventDTO>> events(EventSearch search) {
        ResultBody<List<EventDTO>> resultBody = new ResultBody();
        resultBody.setData(eventDao.eventList(search));
        resultBody.setCount(eventDao.count4EventList(search));
        return resultBody;
    }

    public ResultBody delete(EventDTO info) {
        info.setDeleted(true);
        return Tools.getDeleteResultBody(eventMapper.updateByPrimaryKeySelective(info));
//        return delete(info.getEventId(), eventMapper::deleteByPrimaryKey);
    }

    public ResultBody save(EventDTO info) {
        int ok = 0;
        if (info.getEventId() == null) {
            info.setCreated(new Date());
            ok = eventMapper.insertSelective(info);
        } else {
            ok = eventMapper.updateByPrimaryKeySelective(info);
        }
        return Tools.getSaveResultBody(ok);
    }
    public ResultBody<List<EventDTO>> eventRemindList(EventSearch search){
        return new ResultBody(eventDao.eventRemindList(search));
    }
    //定时任务发送提醒信息
    public void remindMeDoEvents() {
        EventSearch search =new EventSearch();
        List<EventDTO> tasks = eventDao.eventRemindList(search);
        for (EventDTO e : tasks) {
            mailService.sendMimeMessge(e.getEmail(),"you have a event remind",e.getEventContext());
            e.setRemind(false);
            save(e);
        }
    }
}
