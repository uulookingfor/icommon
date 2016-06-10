package com.uulookingfor.icommon.serial;

import java.lang.reflect.Type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author suxiong.sx 
 */
public class JsonUtil {
	
	public static String fastJsonToString(Object o){
		return JSON.toJSONString(o, SerializerFeature.DisableCircularReferenceDetect);
	}
	
	public static <T> T fastJsonToObject(String str, Type type){
		return JSON.parseObject(str, type);
	}
	
}


