package com.daily.Interceptor;

import com.daily.pojo.UserInfo;
import com.daily.service.ICacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {
    private static Logger LOGGER = LoggerFactory.getLogger(UserInterceptor.class);

    private ICacheService cacheService;

    public UserInterceptor(ICacheService cacheService) {
        this.cacheService = cacheService;
    }

    public UserInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        UserInfo user = cacheService.getUserInfo(request);

        if (user != null) {
            return true;
        }
        //设置response状态值为403
//        response.setStatus(403);
        try {
            response.setStatus(403);
        } catch (Exception e) {
            LOGGER.error("拦截器配置出错", e);
            response.setStatus(500);
        }
        //如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
        System.out.println("interceptor url is "+request.getRequestURI());
        response.sendRedirect(request.getContextPath() + "/");

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        System.out.println(request.getRequestURI());
    }
}
