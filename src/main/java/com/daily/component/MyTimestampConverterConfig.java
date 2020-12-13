package com.daily.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

/**
 * @ClassName MyTimestampConverterConfig
 * @Description not use yet.
 * @Author metropolis-long
 * @Date 2020/11/26 21:14
 * @VERSION 1.0.0
 */
//@Configuration
public class MyTimestampConverterConfig {
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;
    @Autowired
    private StringToTimestampConverter stringToTimestampConverter;
    @Autowired
    private StringToUtilDateConverter stringToUtilDateConverter;

    /**
     * converts a string to java.sql.Timestamp and other Date
     */
    @PostConstruct
    public void initEditableAvlidation() {

        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(stringToTimestampConverter);
            genericConversionService.addConverter(stringToUtilDateConverter);
        }

    }
}
