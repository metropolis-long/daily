package com.daily.controller;

import com.daily.dto.CostDTO;
import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.pojo.UserInfo;
import com.daily.search.CostSearch;
import com.daily.search.Page;
import com.daily.service.ICacheService;
import com.daily.service.CostService;
import com.daily.tool.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = {"/api/cost"})
public class CostCtrl {
    @Autowired
    private CostService costService;
    @Autowired
    private ICacheService cacheService;

    /**
     * 我的消费.
     * @param request
     * @param response
     * @param search
     * @return
     */
    @RequestMapping(value = {"/findMyCost"},produces = {"application/json;charset=UTF-8"})
    public Object findMyCost(HttpServletRequest request, HttpServletResponse response, CostSearch search){
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
        ResultBody resultBody =  costService.findMyCost(search);
        return resultBody;
    }
    /**
     * 我的消费统计.
     * @param request
     * @param response
     * @param search
     * @return
     */
    @RequestMapping(value = {"/sumClassify4Cost"},produces = {"application/json;charset=UTF-8"})
    public Object sumClassify4Cost(HttpServletRequest request, HttpServletResponse response, CostSearch search){
        UserInfo userInfo = cacheService.getUserInfo(request);
        search.setUserId(userInfo.getUserId());
        if (!NullUtil.isNull(search.getOrderCause())){
            if (search.getOrderCause().equals("1")){
                search.setOrderCause("created asc");
            }else if ("2".equals(search.getOrderCause())){
                search.setOrderCause("created desc");
            }
        }
        if (search.getPage()==null){
            search.setPage(new Page());
        }
        ResultBody resultBody =  costService.sumClassify4Cost(search);
        return resultBody;
    }
    /**
     * 保存消费.
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = {"/save"},produces = {"application/json;charset=UTF-8"})
    public Object saveDiary(HttpServletRequest request, HttpServletResponse response, CostDTO info){
        UserInfo user = cacheService.getUserInfo(request);
        if (user==null){
            return new ResultBody(ResultCodeMsg.NO_LOGIN.getCode(),ResultCodeMsg.NO_LOGIN.getMsg());
        }
        if (!user.getUserId().equals(info.getUserId())){
            return new ResultBody(ResultCodeMsg.NO_AUTH.getCode(),ResultCodeMsg.NO_AUTH.getMsg());
        }
        ResultBody resultBody =  costService.saveCost(info);
        return resultBody;
    }
}
