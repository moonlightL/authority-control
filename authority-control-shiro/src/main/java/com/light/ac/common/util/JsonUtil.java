package com.light.ac.common.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

    public static String toJSONString(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static Object parseObject(String text, Class<?> clazz) {
        return JSON.parseObject(text, clazz);
    }
}
