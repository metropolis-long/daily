package com.daily.controller;

import com.daily.dto.DiaryDTO;
import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.pojo.UserInfo;
import com.daily.search.DailySearch;
import com.daily.search.Page;
import com.daily.service.ICacheService;
import com.daily.service.IDiaryService;
import com.daily.tool.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = {"/api/diary"})
public class DailyCtrl {
    @Autowired
    private IDiaryService diaryService;
    @Autowired
    private ICacheService cacheService;

    /**
     * my diary list.
     * @param request
     * @param response
     * @param search
     * @return
     */
    @RequestMapping(value = {"/list"},produces = {"application/json;charset=UTF-8"})
    public Object findAllDiary(HttpServletRequest request, HttpServletResponse response, DailySearch search){
        UserInfo user = cacheService.getUserInfo(request);
        if (user==null){
            return new ResultBody(ResultCodeMsg.NO_LOGIN.getCode(),ResultCodeMsg.NO_LOGIN.getMsg());
        }
        if (!user.getUserId().equals(search.getUserId())){
            return new ResultBody(ResultCodeMsg.NO_AUTH.getCode(),ResultCodeMsg.NO_AUTH.getMsg());
        }
        if (!NullUtil.isNull(search.getOrderCause())){
            if (search.getOrderCause().equals("1")){
                search.setOrderCause("d.created asc");
            }else if ("2".equals(search.getOrderCause())){
                search.setOrderCause("d.created desc");
            }
        }
        if (search.getPage()==null){
            search.setPage(new Page());
        }
        ResultBody resultBody =    diaryService.findAllDiary(search);
        return resultBody;
    }
    /**
     * a diary of mine.
     * @param request
     * @param response
     * @param search
     * @return
     */
    @RequestMapping(value = {"/detail"},produces = {"application/json;charset=UTF-8"})
    public Object detail(HttpServletRequest request, HttpServletResponse response, DailySearch search){
        UserInfo user = cacheService.getUserInfo(request);
        if (user==null){
            return new ResultBody(ResultCodeMsg.NO_LOGIN.getCode(),ResultCodeMsg.NO_LOGIN.getMsg());
        }
        if (!user.getUserId().equals(search.getUserId())){
            return new ResultBody(ResultCodeMsg.NO_AUTH.getCode(),ResultCodeMsg.NO_AUTH.getMsg());
        }
        ResultBody resultBody = diaryService.detail(search);
        return resultBody;
    }
    /**
     * save diary.
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = {"/save"},produces = {"application/json;charset=UTF-8"})
    public Object saveDiary(HttpServletRequest request, HttpServletResponse response, DiaryDTO info){
        UserInfo user = cacheService.getUserInfo(request);
        if (user==null){
            return new ResultBody(ResultCodeMsg.NO_LOGIN.getCode(),ResultCodeMsg.NO_LOGIN.getMsg());
        }
        if (!user.getUserId().equals(info.getUserId())){
            return new ResultBody(ResultCodeMsg.NO_AUTH.getCode(),ResultCodeMsg.NO_AUTH.getMsg());
        }
        ResultBody resultBody =  diaryService.saveDiary(info);
        return resultBody;
    }
}
