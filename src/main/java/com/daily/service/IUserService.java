package com.daily.service;

import com.daily.dto.ResultBody;
import com.daily.pojo.DiaryExample;
import com.daily.pojo.UserInfo;

import java.util.List;

public interface IUserService {
    List findAllUser();

    ResultBody login(String loginName, String pwd);

    int save(UserInfo info);
    UserInfo find(UserInfo info);

}
