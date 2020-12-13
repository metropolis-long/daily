package com.daily.service;

import com.daily.dao.auto.UserInfoMapper;
import com.daily.dao.my.UserInfoDao;
import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.pojo.UserInfo;
import com.daily.tool.CypherTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service(value = "userService")
public class UserService {

    @Autowired
    private UserInfoMapper userDao;//这里可能会报错，但是并不会影响

    @Autowired
    private UserInfoDao dao;

    public List findAllUser() {
        return dao.findAllUser();
    }

    //不知道怎么配置缓存条件 condition=""
    //可以在数据库查询方法前写@Cacheable 避免查询 不要跳过整个控制器方法得逻辑
    //可以结合@CachePut方法灵活使用
//  @Cacheable(value = "UserInfo", key = "#loginName", condition="#loginName != null&&#pwd!=null")
    public ResultBody login(String loginName, String pwd) {
        UserInfo search_info = new UserInfo();
        search_info.setUserName(loginName);
        UserInfo info = dao.login(search_info);
        if (info == null) {
            return new ResultBody(ResultCodeMsg.NO_USER.getCode(), ResultCodeMsg.NO_USER.getMsg());
        }
        search_info.setPwd(CypherTools.shaEncode(pwd));
        info = dao.login(search_info);
        if (info == null) {
            return new ResultBody(ResultCodeMsg.LOGIN_PWD_ERR.getCode(), ResultCodeMsg.LOGIN_PWD_ERR.getMsg());
        }
        pwd = null;
        info.setPwd(null);
        ResultBody resultBody = new ResultBody(ResultCodeMsg.OK.getCode(),ResultCodeMsg.OK.getMsg());
        resultBody.setData(info);
        return resultBody;
    }

    public int save(UserInfo info) {
        int ok = 0;
        if (info.getPwd()!=null){
            info.setPwd(CypherTools.shaEncode(info.getPwd()));
        }
        if (info.getUserId() != null ){
            ok=userDao.updateByPrimaryKeySelective(info);
        }else {
            info.setCreated(new Date());
            ok= userDao.insertSelective(info);
        }
        return ok;
    }

    public UserInfo find(UserInfo info) {
        return dao.login(info) ;
    }
}
