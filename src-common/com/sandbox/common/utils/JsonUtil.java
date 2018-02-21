package com.sandbox.common.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static String toJson(Object object){
		if(object instanceof List){
			return JSONArray.fromObject(object).toString();
		}
		return JSONObject.fromObject(object).toString();
	}
	
	public static List toBean(String json , Class<?> clazz){
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<Object> list = new ArrayList<Object>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = (JSONObject)jsonArray.get(i);
			Object object = JSONObject.toBean(jsonObject, clazz);
			if(object!=null){
				list.add(object);
			}
		}
		return list;
	}
	
}
