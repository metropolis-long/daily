package com.daily.controller;

import com.daily.dto.LabelDTO;
import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.pojo.UserInfo;
import com.daily.service.ICacheService;
import com.daily.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LabelCtrl
 * @Description 标签
 * @Author metropolis-long
 * @Date 2020/10/18 18:44
 * @VERSION 1.0.0
 */
@RestController
@RequestMapping(value = {"/api/label"})
public class LabelCtrl {
    @Autowired
    private LabelService labelService;
    @Autowired
    private ICacheService cacheService;
    /**
     * 保存消费.
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = {"/save"},produces = {"application/json;charset=UTF-8"})
    public Object saveLabel(HttpServletRequest request, HttpServletResponse response, LabelDTO info){
        UserInfo user = cacheService.getUserInfo(request);
        if (user==null){
            return new ResultBody(ResultCodeMsg.NO_LOGIN.getCode(),ResultCodeMsg.NO_LOGIN.getMsg());
        }
        if (!user.getUserId().equals(info.getUserId())){
            return new ResultBody(ResultCodeMsg.NO_AUTH.getCode(),ResultCodeMsg.NO_AUTH.getMsg());
        }
        ResultBody resultBody =  labelService.save(info);
        return resultBody;
    }
    /**
     * 删除标签.
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = {"/delete"},produces = {"application/json;charset=UTF-8"})
    public Object deleteLabel(HttpServletRequest request, HttpServletResponse response, LabelDTO info){
        UserInfo userInfo = cacheService.getUserInfo(request);
        if (userInfo==null){
            return new ResultBody(ResultCodeMsg.NO_LOGIN.getCode(),ResultCodeMsg.NO_LOGIN.getMsg());
        }
        info.setUserId(userInfo.getUserId());
        ResultBody resultBody =  labelService.delete(info);
        return resultBody;
    }
}
