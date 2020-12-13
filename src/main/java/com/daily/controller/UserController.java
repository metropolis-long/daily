package com.daily.controller;

import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.dto.UserInfoDTO;
import com.daily.pojo.UserInfo;
import com.daily.service.ICacheService;
import com.daily.service.MailService;
import com.daily.service.UserService;
import com.daily.tool.NullUtil;
import com.daily.tool.NumberTool;
import com.daily.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
/**
 * @ClassName UserController
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/18 19:15
 * @VERSION 1.0.0
 */
@RestController
@RequestMapping(value = {"/api/user"})
public class UserController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;
    @Autowired
    private ICacheService cacheService;

    @RequestMapping(value = {"/findAll"}, produces = {"application/json;charset=UTF-8"})
    public List getAllUsers() {
        List list = userService.findAllUser();
        return list;
    }

    /**
     * 登录
     *
     * @param request
     * @param response
     * @param loginName
     * @param pwd
     * @return
     */
    @RequestMapping(value = {"/login"}, produces = {"application/json;charset=UTF-8"})
    public Object login(HttpServletRequest request, HttpServletResponse response, String loginName, String pwd) {
        if (NullUtil.isNull(loginName) || NullUtil.isNull(pwd)) {
            return new ResultBody(ResultCodeMsg.PARAM_IS_BLANK.getCode(), ResultCodeMsg.PARAM_IS_BLANK.getMsg());
        }
        ResultBody data = userService.login(loginName, pwd);
        UserInfo info = (UserInfo) data.getData();
        if (data.getCode() == 200) {
            String key = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("JSESSIONID", key);
//            cookie.setDomain("daily.zhuyelong.cn");
            cookie.setPath("/");
            response.addCookie(cookie);
            cacheService.setLoginUserInfo(key, request, info);
        }
        return data;

    }

    /**
     * logout system.
     *
     * @param sessionId session cookie value
     * @return msg
     */
    @RequestMapping(value = {"/logout"}, produces = {"application/json;charset=UTF-8"})
    public Object logout(@CookieValue(value = "JSESSIONID") String sessionId) {
        cacheService.outLogin(sessionId);
        return new ResultBody();
    }
    /**
     * user info.
     *
     * @return msg
     */
    @RequestMapping(value = {"/info"}, produces = {"application/json;charset=UTF-8"})
    public Object getUser(HttpServletRequest request) {
        UserInfo user = cacheService.getUserInfo(request);
        if (NullUtil.isNull(user)){
            return new ResultBody(ResultCodeMsg.NO_LOGIN.getCode(),ResultCodeMsg.NO_LOGIN.getMsg());
        }
        return new ResultBody(user);
    }
    @RequestMapping(value = {"/save"}, produces = {"application/json;charset=UTF-8"})
    public Object save(HttpServletRequest request, UserInfoDTO info) {
        UserInfo user = cacheService.getUserInfo(request);
        if (NullUtil.isNull(user)){
            return new ResultBody(ResultCodeMsg.NO_LOGIN.getCode(),ResultCodeMsg.NO_LOGIN.getMsg());
        }
        if (!user.getUserId().equals(info.getUserId())){
            return new ResultBody(ResultCodeMsg.NO_AUTH.getCode(),ResultCodeMsg.NO_AUTH.getMsg());
        }
        UserInfo find = userService.find(info);
        if (!NullUtil.isNull(find) && !find.getEmail().equals(user.getEmail()) && find.getPhone()!=null &&!find.getPhone().equals(user.getPhone())){
            return new ResultBody(ResultCodeMsg.HAS_USER_REGISTER.getCode(),ResultCodeMsg.HAS_USER_REGISTER.getMsg());
        }
        info.setUserId(user.getUserId());
        int ok = userService.save(info);
        cacheService.setLoginUserInfo(request, info);
        return Tools.getSaveResultBody(ok);
    }
    /**
     * register.
     *
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = {"/reg"}, produces = {"application/json;charset=UTF-8"})
    public Object register(HttpServletRequest request, HttpServletResponse response, UserInfoDTO info) {
        if (NullUtil.isNull(info) || NullUtil.isNull(info.getEmail()) || NullUtil.isNull(info.getPwd())) {
            return new ResultBody(ResultCodeMsg.REG_EMAIL_ERR.getCode(), ResultCodeMsg.REG_EMAIL_ERR.getMsg());
        }
        String myCode = (String) request.getSession().getAttribute(info.getEmail());
        if (NullUtil.isNull(info.getCode())|| !info.getCode().equalsIgnoreCase(myCode)) {
            return new ResultBody(ResultCodeMsg.REG_CODE_ERR.getCode(), ResultCodeMsg.REG_CODE_ERR.getMsg());
        }
        UserInfo search = new UserInfo();
        search.setUserName(info.getEmail());
        UserInfo user = userService.find(search);
        if (!NullUtil.isNull(user)){
            return new ResultBody(ResultCodeMsg.HAS_USER_REGISTER.getCode(),ResultCodeMsg.HAS_USER_REGISTER.getMsg());
        }
        int ok = userService.save(info);
        System.out.println(ResultCodeMsg.OK.getCode() + ResultCodeMsg.OK.getMsg());
        ResultBody resultBody = ok > 0 ? new ResultBody(ResultCodeMsg.OK.getCode(), ResultCodeMsg.OK.getMsg()) : new ResultBody(ResultCodeMsg.REG_ERR.getCode(), ResultCodeMsg.REG_ERR.getMsg());
        return resultBody;
    }

    /**
     * register.
     *
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = {"/reg/get/code"}, produces = {"application/json;charset=UTF-8"})
    public Object registerByCode(HttpServletRequest request, HttpServletResponse response,final UserInfoDTO info) {
        if (NullUtil.isNull(info) || NullUtil.isNull(info.getEmail())) {
            return new ResultBody(ResultCodeMsg.PARAM_IS_BLANK.getCode(), ResultCodeMsg.PARAM_IS_BLANK.getMsg());
        }
        final String code = NumberTool.getStrRamon(6);
        System.out.println("register code is "+code);
        request.getSession().setMaxInactiveInterval(100);
        request.getSession().setAttribute(info.getEmail(),code);
        final String msg = "welcome to register our system . " +
                "<br> your register code is <span style='color:green'>  "+code +" </span>," +
                "<br>thanks !";
        //exec thread to speed respond
        new Thread(()->{
            mailService.sendMimeMessge(info.getEmail(),"register code",msg);
            System.out.println("send email over .");
        }).start();
        return new ResultBody(ResultCodeMsg.OK.getCode(), ResultCodeMsg.OK.getMsg());
    }


}
