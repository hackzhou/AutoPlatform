package com.auto.test.utils;

import com.alibaba.fastjson.JSON;

public class JSONUtil {
	
	public static <T> T json2Bean(Class<T> c, String text){
		return JSON.parseObject(text, c);
	}
	
	public static <T> String bean2JSON(T t, boolean format){
		return JSON.toJSONString(t, format);
	}
	
}
