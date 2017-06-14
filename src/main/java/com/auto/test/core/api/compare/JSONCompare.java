package com.auto.test.core.api.compare;

import java.util.Iterator;
import java.util.Set;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONCompare {
	
	public static void main(String[] args) {
//		String s1 = "{\"code\":200,\"data\":{\"loginname\":\"13151815253\",\"phone\":\"13151815253\",\"headImg\":\"\",\"nicknameFlag\":false,\"nickname\":\"zhouzhou\",\"userId\":166948,\"useAmount\":2035.0,\"userType\":1}}";
//		String s2 = "{\"code\":200,\"data\":{\"loginname\":\"13151815253\",\"phone\":\"13151815253\",\"headImg\":\"http://192.168.101.242/cdn/c7ea/1334/16a3/4de7/b3da/426c/802a/5b05/c7ea133416a34de7b3da426c802a5b05.png\",\"nicknameFlag\":false,\"nickname\":\"zhouzhou\",\"userId\":37,\"useAmount\":1.31451443E8,\"userType\":1}}";
//		System.out.println(new JSONCompare().compareJson(JSON.parse(s1), JSON.parse(s2), "headImg,userId,useAmount".split(",")));
//		String s1 = "{\"code\": 200,\"data\":[{\"changeMoney\": 7521,\"createTime\": \"2017年03月21日 00:01\",\"name\": \"平台\"},{\"changeMoney\": 2820,\"createTime\": \"2017年03月20日 21:09\",\"name\": \"梦想飞镖\"},{\"changeMoney\": 500,\"createTime\": \"2017年03月20日 21:09\",\"name\": \"梦想飞镖\"}]}";
//		String s2 = "{\"code\": 200,\"data\":[{\"changeMoney\": 2820,\"createTime\": \"2017年03月20日 21:09\",\"name\": \"梦想飞镖\"},{\"changeMoney\": 7521,\"createTime\": \"2017年03月21日 00:01\",\"name\": \"平台\"},{\"changeMoney\": 500,\"createTime\": \"2017年03月20日 21:09\",\"name\": \"梦想飞镖\"}]}";
//		System.out.println(new JSONCompare().compareJson(JSON.parse(s1), JSON.parse(s2), "headImg,userId,useAmount".split(",")));
		
		String s1 = "{\"code\": 200,\"data\":[{\"changeMoney\": 7521,\"createTime\": \"2017年03月21日 00:01\",\"name\": \"平台\"},{\"changeMoney\": 2820,\"createTime\": \"2017年03月20日 21:09\",\"name\": \"梦想飞镖\"},{\"changeMoney\": 500,\"createTime\": \"2017年03月20日 21:09\",\"name\": \"梦想飞镖\"}]}";
		String s2 = "{\"code\": 200,\"data\":[{\"createTime\": \"2017年03月20日 21:09\",\"changeMoney\": 2820,\"name\": \"梦想飞镖\"},{\"name\": \"平台\",\"createTime\": \"2017年03月21日 00:01\",\"changeMoney\": 7521},{\"changeMoney\": 500,\"name\": \"梦想飞镖\",\"createTime\": \"2017年03月20日 21:09\"}]}";
		String s3 = "{\"changeMoney\": 7521,\"createTime\": \"2017年03月21日 00:01\",\"name\": \"平台\"}";
		String s4 = "{\"createTime\": \"2017年03月21日 00:01\",\"changeMoney\": 7521,\"name\": \"平台\"}";
		System.out.println(new JSONCompare().sortJson(s1));
		System.out.println(new JSONCompare().sortJson(s2));
		System.out.println(new JSONCompare().sortJson(s3));
		System.out.println(new JSONCompare().sortJson(s4));
	}
	
	/**
	 * JSON Compare
	 * @param json1
	 * @param json2
	 * @param ignore
	 * @return
	 */
	public boolean compareJson(String json1, String json2, String[] ignore){
		if(json1 == null || json2 == null){
			return false;
		}
		return compareJson(JSON.parse(json1), JSON.parse(json2), ignore);
	}
	
	public boolean compareJson(Object json1, Object json2, String[] ignore){
		try {
			if(json1 instanceof JSONObject){
				if(json2 instanceof JSONObject){
					return compareJson((JSONObject) json1, (JSONObject) json2, ignore);
				}
			}else if(json1 instanceof JSONArray){
				if(json2 instanceof JSONArray){
					return compareJson((JSONArray) json1, (JSONArray) json2, ignore);
				}
			}else if(json1 instanceof String){
				if(json2 instanceof String){
					return compareJson((String) json1, (String) json2);
				}
			}else{
				return compareJson(String.valueOf(json1), String.valueOf(json2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean compareJson(JSONObject json1, JSONObject json2, String[] ignore) {
		if(json1.size() == json2.size()){
			Set<String> set1 = json1.keySet();
			Set<String> set2 = json2.keySet();
			if(set1.size() == set2.size()){
				if(set1.size() > 0){
					for (String s : set1) {
						if(!contain(s, ignore)){
							if(!compareJson(json1.get(s), json2.get(s), ignore)){
								return false;
							}
						}
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean compareJson(JSONArray json1, JSONArray json2, String[] ignore) {
		if(json1.size() == json2.size()){
			if(!json1.isEmpty()){
				json1.sort((o1, o2) -> o1.toString().compareTo(o2.toString()));
				json2.sort((o1, o2) -> o1.toString().compareTo(o2.toString()));
				Iterator<?> i1 = json1.iterator();
				Iterator<?> i2 = json2.iterator();
				while (i1.hasNext()) {
					if(!compareJson(i1.next(), i2.next(), ignore)){
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean compareJson(String str1, String str2) {
        if(str1.equals(str2)) {
        	return true;
        }
        return false;
    }
	
	public boolean contain(String text, String[] ignore){
		if(ignore != null && ignore.length > 0){
			for (String str : ignore) {
				if(str.equalsIgnoreCase(text)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * JSON Sort
	 * @param json
	 * @return
	 */
	public String sortJson(String json){
		try {
			if(json != null && !json.isEmpty()){
				Object obj = JSON.parse(json);
				sortJson(obj);
				return JSON.toJSONString(obj, SerializerFeature.SortField);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public void sortJson(Object json){
		try {
			if(json instanceof JSONObject){
				sortJson((JSONObject) json);
			}else if(json instanceof JSONArray){
				sortJson((JSONArray) json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sortJson(JSONObject json) {
		Set<String> set = json.keySet();
		if(set != null && set.size() > 0){
			for (String s : set) {
				sortJson(json.get(s));
			}
		}
	}
	
	public void sortJson(JSONArray json) {
		json.sort((o1, o2) -> o1.toString().compareTo(o2.toString()));
	}

}
