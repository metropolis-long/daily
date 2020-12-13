package com.daily.service;

import com.daily.dto.UserInfoDTO;
import com.daily.pojo.CitiesCity;
import com.daily.pojo.CitiesProvince;
import com.daily.pojo.SpringScheduledTask;
import com.daily.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICacheService {
    UserInfo getUserInfo(HttpServletRequest request);
    void setLoginUserInfo(String key,HttpServletRequest request,UserInfo info);

    void outLogin(String sessionId);
    List<CitiesCity> city();
    List<CitiesProvince> province();

    void setLoginUserInfo(HttpServletRequest request, UserInfoDTO info);

    List<CitiesCity> city(Long provinceCode);
    List<SpringScheduledTask> findAll();

    SpringScheduledTask findByCronKey(String cronKey);
}
