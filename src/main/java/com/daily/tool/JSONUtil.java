package com.daily.tool;
/*
 * Copyright (c) 2019, lanqiao.org
 *
 * All rights reserved.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON转换类.
 *
 * @author Acci (Chunsun Qin)
 * @version 1.0.0.0, 2019/6/12
 */
public class JSONUtil {
    public static String toJson(Object obj){
        final ObjectMapper mapper=new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args){
        //t();
    }
}
