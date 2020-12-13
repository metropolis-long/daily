package com.daily.dao.my;

import com.daily.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
public interface UserInfoDao {
    public UserInfo login(UserInfo search_info);
    public List findAllUser();
}
