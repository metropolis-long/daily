package com.daily.controller;

import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.dto.TravelDTO;
import com.daily.pojo.UserInfo;
import com.daily.search.Page;
import com.daily.search.TravelSearch;
import com.daily.service.ICacheService;
import com.daily.service.TravelNoteService;
import com.daily.tool.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @ClassName EventDTO
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/18 19:15
 * @VERSION 1.0.0
 */
@RestController
@RequestMapping(value = {"/api/travel"})
public class TravelNoteCtrl {
    @Autowired
    private TravelNoteService travelNoteService;
    @Autowired
    private ICacheService cacheService;


    /**
     * travel note list.
     *
     * @param request
     * @param response
     * @param search
     * @return
     */
    @RequestMapping(value = {"/list"}, produces = {"application/json;charset=UTF-8"})
    public Object findMyLearn(HttpServletRequest request, HttpServletResponse response, TravelSearch search) {
        UserInfo userInfo = cacheService.getUserInfo(request);
        search.setUserId(userInfo.getUserId());
        if (!NullUtil.isNull(search.getOrderCause())) {
            if (search.getOrderCause().equals("1")) {
                search.setOrderCause("d.created asc");
            } else if ("2".equals(search.getOrderCause())) {
                search.setOrderCause("d.created desc");
            }
        }
        if (search.getPage() == null) {
            search.setPage(new Page());
        }
        ResultBody resultBody = travelNoteService.findAllTravel(search);
        return resultBody;
    }

    /**
     * ravel note's detail.
     *
     * @param request
     * @param response
     * @param search
     * @return
     */
    @RequestMapping(value = {"/detail"}, produces = {"application/json;charset=UTF-8"})
    public Object detail(HttpServletRequest request, HttpServletResponse response, TravelSearch search) {
        if (NullUtil.isNull(search.getTravelId()) || NullUtil.isNull(search.getUserId())) {
            return new ResultBody(ResultCodeMsg.PARAM_IS_BLANK.getCode(), ResultCodeMsg.PARAM_IS_BLANK.getMsg());
        }
        UserInfo userInfo = cacheService.getUserInfo(request);
        search.setUserId(userInfo.getUserId());
        ResultBody resultBody = travelNoteService.detail(search);
        return resultBody;
    }

    /**
     * save my travel note.
     *
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = {"/save"}, produces = {"application/json;charset=UTF-8"})
    public Object saveTravel(HttpServletRequest request, HttpServletResponse response, TravelDTO info) {
        UserInfo user = cacheService.getUserInfo(request);
        if (user == null) {
            return new ResultBody(ResultCodeMsg.NO_LOGIN.getCode(), ResultCodeMsg.NO_LOGIN.getMsg());
        }
        if (!user.getUserId().equals(info.getUserId())) {
            return new ResultBody(ResultCodeMsg.NO_AUTH.getCode(), ResultCodeMsg.NO_AUTH.getMsg());
        }
        info.setUserId(user.getUserId());
        ResultBody resultBody = travelNoteService.saveTravelNote(info);
        return resultBody;
    }
}
