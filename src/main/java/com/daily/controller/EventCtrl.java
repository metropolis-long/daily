package com.daily.controller;

import com.daily.dto.EventDTO;
import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.pojo.UserInfo;
import com.daily.search.EventSearch;
import com.daily.search.Page;
import com.daily.service.ICacheService;
import com.daily.service.EventService;
import com.daily.tool.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName EventCtrl
 * @Description 事件
 * @Author metropolis-long
 * @Date 2020/10/18 18:44
 * @VERSION 1.0.0
 */
@RestController
@RequestMapping("/api/event")
public class EventCtrl {
    @Autowired
    private ICacheService cacheService;
    @Autowired
    private EventService eventService;

    /**
     * 我的记事本.
     * @param request
     * @param response
     * @param search
     * @return
     */
    @RequestMapping(value = {"/eventList"},produces = {"application/json;charset=UTF-8"})
    public Object eventList(HttpServletRequest request, HttpServletResponse response, EventSearch search){
        UserInfo userInfo = cacheService.getUserInfo(request);
        search.setUserId(userInfo.getUserId());
        if (!NullUtil.isNull(search.getOrderCause())){
            if (search.getOrderCause().equals("1")){
                search.setOrderCause("created asc");
            }else if ("2".equals(search.getOrderCause())){
                search.setOrderCause("created desc");
            }
        }
        ResultBody resultBody = eventService.events(search);
        return resultBody;
    }
    @RequestMapping(value = {"/eventRemindList"},produces = {"application/json;charset=UTF-8"})
    public Object eventRemindList(HttpServletRequest request, HttpServletResponse response, EventSearch search){
        UserInfo userInfo = cacheService.getUserInfo(request);
        search.setUserId(userInfo.getUserId());
        ResultBody resultBody = eventService.eventRemindList(search);
        return resultBody;
    }

    /**
     * 保存事件.
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = {"/save"},produces = {"application/json;charset=UTF-8"})
    public Object saveLabel(HttpServletRequest request, HttpServletResponse response, EventDTO info){
        UserInfo userInfo = cacheService.getUserInfo(request);
        System.out.println("bm4==========="+info.getEventContext());
        if (userInfo==null){
            return new ResultBody(ResultCodeMsg.NO_LOGIN.getCode(),ResultCodeMsg.NO_LOGIN.getMsg());
        }
        if (!userInfo.getUserId().equals(info.getUserId())){
            return new ResultBody(ResultCodeMsg.NO_AUTH.getCode(),ResultCodeMsg.NO_AUTH.getMsg());
        }
        ResultBody resultBody =  eventService.save(info);
        return resultBody;
    }
    /**3月🖥到期
     * 删除事件.
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = {"/delete"},produces = {"application/json;charset=UTF-8"})
    public Object deleteLabel(HttpServletRequest request, HttpServletResponse response, EventDTO info){
        UserInfo userInfo = cacheService.getUserInfo(request);
        if (!userInfo.getUserId().equals(info.getUserId())){
            return new ResultBody(ResultCodeMsg.NO_AUTH.getCode(),ResultCodeMsg.NO_AUTH.getMsg());
        }
        ResultBody resultBody =  eventService.delete(info);
        return resultBody;
    }
    /**3月🖥到期
     * 删除事件.
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = {"/sister"},produces = {"application/json;charset=UTF-8"})
    public Object sister(HttpServletRequest request, HttpServletResponse response, EventDTO info){
        ResultBody resultBody =  eventService.sister(info);
        return resultBody;
    }
}
