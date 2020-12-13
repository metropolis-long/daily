package com.daily.tool;
/*
 * Copyright (c) 2019, com.lanqiao.org
 *
 * All rights reserved.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记是否为sqlite的表字段.
 *
 * @author Administrator
 * @version 1.0.0.0, 2019/7/2 0002
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableName {
    String value();
}
