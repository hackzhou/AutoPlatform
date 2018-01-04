package com.auto.test.core.api.compare;

import java.util.Set;
import java.util.Iterator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONCompare {

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
		if(json1.size() != json2.size()){
			if(ignore != null && ignore.length > 0){
				for (String s : ignore) {
					json1.remove(s);
					json2.remove(s);
				}
			}
		}
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
		if(str1.startsWith("共") && str1.contains("个错误；") && str1.contains("，")){
			return compareDisorder(str1, str2);
		}
        return false;
    }
	
	public boolean compareDisorder(String str1, String str2){
		if(str1.length() == str2.length()){
			String[] arr = str1.split("；")[1].split("，");
			for (String arrStr : arr) {
				if(!str2.contains(arrStr)) {
					return false;
				}
			}
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
				Object obj = JSON.parse(JSON.toJSONString(JSON.parse(json), SerializerFeature.SortField));
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
		Iterator<Object> it = json.iterator();
		while (it.hasNext()) {
			sortJson(it.next());
		}
	}
	
	public static void main(String[] args) {
//		String s1 = "{\"code\":200,\"data\":{\"loginname\":\"13151815253\",\"phone\":\"13151815253\",\"headImg\":\"\",\"nicknameFlag\":false,\"nickname\":\"zhouzhou\",\"userId\":166948,\"useAmount\":2035.0,\"userType\":1}}";
//		String s2 = "{\"code\":200,\"data\":{\"loginname\":\"13151815253\",\"phone\":\"13151815253\",\"headImg\":\"http://192.168.101.242/cdn/c7ea/1334/16a3/4de7/b3da/426c/802a/5b05/c7ea133416a34de7b3da426c802a5b05.png\",\"nicknameFlag\":false,\"nickname\":\"zhouzhou\",\"userId\":37,\"useAmount\":1.31451443E8,\"userType\":1}}";
//		System.out.println(new JSONCompare().compareJson(JSON.parse(s1), JSON.parse(s2), "headImg,userId,useAmount".split(",")));
//		String s1 = "{\"code\": 200,\"data\":[{\"changeMoney\": 7521,\"createTime\": \"2017年03月21日 00:01\",\"name\": \"平台\"},{\"changeMoney\": 2820,\"createTime\": \"2017年03月20日 21:09\",\"name\": \"梦想飞镖\"},{\"changeMoney\": 500,\"createTime\": \"2017年03月20日 21:09\",\"name\": \"梦想飞镖\"}]}";
//		String s2 = "{\"code\": 200,\"data\":[{\"changeMoney\": 2820,\"createTime\": \"2017年03月20日 21:09\",\"name\": \"梦想飞镖\"},{\"changeMoney\": 7521,\"createTime\": \"2017年03月21日 00:01\",\"name\": \"平台\"},{\"changeMoney\": 500,\"createTime\": \"2017年03月20日 21:09\",\"name\": \"梦想飞镖\"}]}";
//		System.out.println(new JSONCompare().compareJson(JSON.parse(s1), JSON.parse(s2), "headImg,userId,useAmount".split(",")));
		
		String s1 = "{\"code\":200,\"data\":{\"feedback\":[],\"common\":[{\"amount\":1000,\"bizType\":101,\"thirdId\":\"jddgame_0001\",\"price\":1,\"bizId\":241,\"name\":\"清凉啤酒\",\"icon\":\"/group1/M00/00/11/wKhl_VnbNyGASP-8AAASLhjBORI821.png\",\"productIcon\":\"/group1/M00/00/02/wKhl_VmIF9iAASr_AAASLhjBORI008.png\",\"remark\":\"\",\"bizTarget\":241}],\"welfare\":[],\"feedbackAutoOpen\":false,\"card\":[{\"bizType\":106,\"thirdId\":\"jddgame_0001\",\"nextTime\":0,\"buyFlag\":1,\"price\":30,\"icon\":\"/group1/M00/00/11/wKhl_VnbNzyAcQpqAAAb-Wq8eME950.png\",\"bizId\":242,\"name\":\"周卡\",\"remark\":\"\",\"productIcon\":\"/group1/M00/00/03/wKhl_VmIHuaAaE2XAAAb-Wq8eME107.png\",\"bizTarget\":242,\"content\":\"购买立得:5000;每天返还:5000\"}]}}";
		String s2 = "{\"code\":200,\"data\":{\"feedback\":[],\"common\":[{\"bizId\":241,\"name\":\"清凉啤酒\",\"price\":1,\"amount\":1000,\"giveAmount\":null,\"icon\":\"/group1/M00/00/11/wKhl_VnbNyGASP-8AAASLhjBORI821.png\",\"productIcon\":\"/group1/M00/00/02/wKhl_VmIF9iAASr_AAASLhjBORI008.png\",\"thirdId\":\"jddgame_0001\",\"remark\":\"\",\"bizType\":101,\"bizTarget\":241}],\"card\":[{\"bizId\":242,\"name\":\"周卡\",\"price\":30,\"giveAmount\":null,\"content\":\"购买立得:5000;每天返还:5000\",\"icon\":\"/group1/M00/00/11/wKhl_VnbNzyAcQpqAAAb-Wq8eME950.png\",\"productIcon\":\"/group1/M00/00/03/wKhl_VmIHuaAaE2XAAAb-Wq8eME107.png\",\"thirdId\":\"jddgame_0001\",\"remark\":\"\",\"nextTime\":0,\"buyFlag\":1,\"bizType\":106,\"bizTarget\":242}],\"welfare\":[],\"feedbackAutoOpen\":false}}";
		System.out.println(new JSONCompare().compareJson(new JSONCompare().sortJson(s1), new JSONCompare().sortJson(s2), null));
		System.out.println(new JSONCompare().sortJson(s1));
		System.out.println(new JSONCompare().sortJson(s2));
		String s3 = "{\"code\":200,\"data\":[{\"method\":\"ali\",\"wapImg\":\"/cdn/mall/ali-wappay.png\",\"appImg\":\"/cdn/mall/ali-apppay.png\",\"isH5\":true,\"name\":\"支付宝支付\",\"value\":\"4\",\"desc\":\"推荐使用\"},{\"method\":\"wx\",\"wapImg\":\"/cdn/mall/wx-wappay.png\",\"appImg\":\"/cdn/mall/wx-apppay.png\",\"isH5\":true,\"name\":\"微信支付\",\"value\":\"3\"}]}";
		String s4 = "{\"code\":200,\"data\":[{\"method\":\"wx\",\"wapImg\":\"/cdn/mall/wx-wappay.png\",\"appImg\":\"/cdn/mall/wx-apppay.png\",\"isH5\":true,\"name\":\"微信支付\",\"value\":\"3\"},{\"method\":\"ali\",\"wapImg\":\"/cdn/mall/ali-wappay.png\",\"appImg\":\"/cdn/mall/ali-apppay.png\",\"isH5\":true,\"name\":\"支付宝支付\",\"value\":\"4\",\"desc\":\"推荐使用\"}]}";
		System.out.println(new JSONCompare().sortJson(s3));
		System.out.println(new JSONCompare().sortJson(s4));
	}

}
