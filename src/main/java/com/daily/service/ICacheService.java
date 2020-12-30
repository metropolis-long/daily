package com.daily.service;

import com.daily.dto.UserInfoDTO;
import com.daily.pojo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICacheService {
    UserInfo getUserInfo(HttpServletRequest request);

    void setLoginUserInfo(String key, HttpServletRequest request, UserInfo info);

    void outLogin(String sessionId);

    public List<CitiesCounty> county();

    List<CitiesCity> city();

    List<CitiesProvince> province();

    void setLoginUserInfo(HttpServletRequest request, UserInfoDTO info);

    List<CitiesCity> city(Long provinceCode);

    List<SpringScheduledTask> findAll();

    SpringScheduledTask findByCronKey(String cronKey);

    List<CitiesCounty> county(Long cityCode);
}
