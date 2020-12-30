package com.daily.service;

import com.daily.dao.auto.CitiesCityMapper;
import com.daily.dao.auto.CitiesCountyMapper;
import com.daily.dao.auto.CitiesProvinceMapper;
import com.daily.dao.my.SpringScheduledTaskDao;
import com.daily.dto.UserInfoDTO;
import com.daily.pojo.*;
import com.daily.tool.NullUtil;
import com.daily.tool.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @ClassName CacheService
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/18 19:27
 * @VERSION 1.0.0
 */
@Service(value = "cacheService")
public class CacheService implements ICacheService {
    @Resource
    private CitiesProvinceMapper citiesProvinceMapper;

    @Resource
    private CitiesCityMapper citiesCityMapper;

    @Resource
    private CitiesCountyMapper citiesCountyMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private SpringScheduledTaskDao springScheduledTaskDao;
    /**
     * 获取缓存登录信息.
     * @param request
     * @return
     */
    @Override
    public UserInfo getUserInfo(HttpServletRequest request) {
        String key= getCookie(request);
        UserInfo info=(UserInfo)redisUtil.get(key);
        return info;
    }

    /**
     * 设置登录会话缓存.
     * @param request
     * @param info
     * @return cookie
     */
    @Override
    public void setLoginUserInfo(String key,HttpServletRequest request,UserInfo info) {
        redisUtil.set(key,info,60*60*24);
    }

    @Override
    public void outLogin(String sessionId) {
        redisUtil.del(sessionId);

    }
    @Override
    public List<CitiesCounty> county() {
        List<CitiesCounty> county = (List<CitiesCounty>) redisUtil.get("county");
        if (NullUtil.listIsNull(county)){
            CitiesCountyExample e =new CitiesCountyExample();
            county =citiesCountyMapper.selectByExample(e);
            redisUtil.set("county",county);
        }
        return county;
    }
    @Override
    public List<CitiesCity> city() {
        List<CitiesCity> cities = (List<CitiesCity>) redisUtil.get("city");
        if (NullUtil.listIsNull(cities)){
            CitiesCityExample e =new CitiesCityExample();
            cities =citiesCityMapper.selectByExample(e);
            redisUtil.set("city",cities);
        }
        return cities;
    }

    @Override
    public List<CitiesProvince> province() {
        List<CitiesProvince> province = (List<CitiesProvince>) redisUtil.get("province");
        if (NullUtil.listIsNull(province)){
            CitiesProvinceExample e =new CitiesProvinceExample();
            province =citiesProvinceMapper.selectByExample(e);
            redisUtil.set("province",province);
        }
        return province;
    }

    @Override
    public void setLoginUserInfo(HttpServletRequest request, UserInfoDTO info) {
        redisUtil.set(getCookie(request),info,60*60*24);
    }
    @Override
    public List<CitiesCity> city(Long provinceCode) {
        if (NullUtil.isNull(provinceCode)){
            return city();
        }
        List<CitiesCity> cities = (List<CitiesCity>) redisUtil.get("city_"+provinceCode);
        if (NullUtil.listIsNull(cities)){
            CitiesCityExample e =new CitiesCityExample();
            e.createCriteria().andProvinceCodeEqualTo(provinceCode);
            cities =citiesCityMapper.selectByExample(e);
            redisUtil.set("city_"+provinceCode,cities);
        }
        return cities;
    }

    @Override
    public List<SpringScheduledTask> findAll() {
        List<SpringScheduledTask> list = (List<SpringScheduledTask>) redisUtil.get("tack_list");
        if (NullUtil.listIsNull(list)){
            System.out.println("ca999999999999999");
            list = springScheduledTaskDao.findAll();
            redisUtil.set("tack_list",list);
        }
        return list;
    }

    @Override
    public SpringScheduledTask findByCronKey(String cronKey) {
        SpringScheduledTask list = (SpringScheduledTask) redisUtil.hget(cronKey,"tacks");
        if (NullUtil.isNull(list)){
            System.out.println("cache............");
            list = springScheduledTaskDao.findByCronKey(cronKey);
            redisUtil.hset(cronKey,"tacks",list);
        }
        return list;
    }

    @Override
    public List<CitiesCounty> county(Long cityCode) {
        if (cityCode==null){
            return  county();
        }
        List<CitiesCounty> cities = (List<CitiesCounty>) redisUtil.get("county_"+cityCode);
        if (NullUtil.listIsNull(cities)){
            CitiesCountyExample e =new CitiesCountyExample();
            e.createCriteria().andCityCodeEqualTo(cityCode);
            cities =citiesCountyMapper.selectByExample(e);
            redisUtil.set("county_"+cityCode,cities);
        }
        return cities;
    }

    /**
     * 获取JSESSIONID.
     * @param request
     * @return
     */
    private String getCookie(final HttpServletRequest request){
        final Cookie[] cookies =request.getCookies();
        if (NullUtil.isNull(cookies)){
            return "";
        }
        for (Cookie cookie:cookies){
            System.out.println(cookie.getName()+":::"+cookie.getValue());
            if ("JSESSIONID".equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return "";
    }
}
