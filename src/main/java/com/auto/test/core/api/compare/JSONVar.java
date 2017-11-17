package com.auto.test.core.api.compare;

import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONVar {
	private static final String DATA_VAR = "\"_DATA_VAR\"";
	
	public static void main(String[] args) {
		String r = "{\"code\":200,\"data\":{\"awardId\":1505,\"playId\":98047,\"configId\":2618,\"useAmount\":997922834,\"awardType\":9}}";
		String p = "{\"recName\":\"周洲\",\"recMobile\":\"13151815253\",\"playId\":\"_DATA_VAR\",\"recAddress\":\"苏州工业园区\"}";
		System.out.println(new JSONVar().replaceBody(p, r));
	}
	
	public String replaceBody(String p, String r){
		if(p == null || p.isEmpty() || !p.contains(DATA_VAR)){
			return p;
		}
		String v = getValByResult(p, r);
		if(v != null && !v.isEmpty()){
			return p.replace("\"_DATA_VAR\"", "\"" + v + "\"");
		}
		return p.replace("\"_DATA_VAR\"", "\"1\"");
	}
	
	public String getValByResult(String p, String r){
		String b = getVarData(p);
		if(b != null && !b.isEmpty()){
			b = "\"" + b + "\":";
			if(r != null && r.contains(b)){
				String s = r.split(b)[1];
				if(s != null && !s.isEmpty()){
					return s.split(",")[0].replace("\"", "").replace("}", "");
				}
			}
		}
		return "";
	}
	
	public String getVarData(String p){
		JSONObject obj = (JSONObject) JSON.parse(p);
		if(obj.size() > 0){
			Set<String> set = obj.keySet();
			for (String str : set) {
				if("_DATA_VAR".equals(obj.get(str))){
					return str;
				}
			}
		}
		return "";
	}

}
