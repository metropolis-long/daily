package com.daily.service;

import com.daily.msg.ResultBody;
import com.daily.tool.Tools;

import java.util.function.Function;

/**
 * @ClassName BaseService
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/18 19:27
 * @VERSION 1.0.0
 */
public class BaseService {
    /**
     * 统一删除方法。
     * @param id
     * @param method
     * @return
     */
    public ResultBody delete(Long id, Function<Long,Integer> method){
        int ok= method.apply(id);
        return Tools.getDeleteResultBody(ok);
    }
}
