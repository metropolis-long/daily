package com.daily.controller;

import com.daily.dto.LearnDTO;
import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.pojo.UserInfo;
import com.daily.search.LearnSearch;
import com.daily.search.Page;
import com.daily.service.LearnService;
import com.daily.service.ICacheService;
import com.daily.tool.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName LearnCtrl
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/18 19:15
 * @VERSION 1.0.0
 */
@RestController
@RequestMapping(value = {"/api/learn"})
public class LearnCtrl {
    @Autowired
    private ICacheService cacheService;

    @Autowired
    private LearnService learnService;

    /**
     * learn note list.
     * @param request
     * @param response
     * @param search
     * @return
     */
    @RequestMapping(value = {"/findLearn"},produces = {"application/json;charset=UTF-8"})
    public Object findMyLearn(HttpServletRequest request, HttpServletResponse response, LearnSearch search){
        UserInfo userInfo = cacheService.getUserInfo(request);
        search.setUserId(userInfo.getUserId());
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
        ResultBody<List<LearnDTO>>  resultBody =    learnService.findMyLearn(search);
        System.out.println("learn size ;;;;;;   "+resultBody.getData().size());
        return resultBody;
    }
    /**
     * learn note detail.
     * @param request
     * @param response
     * @param search
     * @return
     */
    @RequestMapping(value = {"/detail"},produces = {"application/json;charset=UTF-8"})
    public Object detail(HttpServletRequest request, HttpServletResponse response, LearnSearch search){
        if (NullUtil.isNull(search.getLearnId())){
            return new ResultBody(ResultCodeMsg.PARAM_IS_BLANK.getCode(),ResultCodeMsg.PARAM_IS_BLANK.getMsg());
        }
        UserInfo userInfo = cacheService.getUserInfo(request);
        search.setUserId(userInfo.getUserId());
        ResultBody resultBody = learnService.detail(search);
        return resultBody;
    }
    /**
     * save my learn note.
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = {"/save"},produces = {"application/json;charset=UTF-8"})
    public Object saveDiary(HttpServletRequest request, HttpServletResponse response, LearnDTO info){
        UserInfo userInfo = cacheService.getUserInfo(request);
        if (userInfo==null){
            return new ResultBody(ResultCodeMsg.NO_LOGIN.getCode(),ResultCodeMsg.NO_LOGIN.getMsg());
        }
        if (!userInfo.getUserId().equals(info.getUserId())){
            return new ResultBody(ResultCodeMsg.NO_AUTH.getCode(),ResultCodeMsg.NO_AUTH.getMsg());
        }
        info.setUserId(userInfo.getUserId());
        ResultBody resultBody =  learnService.save(info);
        return resultBody;
    }
}
