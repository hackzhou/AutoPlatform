package com.auto.test.utils;

import org.apache.commons.lang3.ArrayUtils;
import com.auto.test.common.config.GlobalValueConfig;

public class ArrayUtil {
	private static final String URL_ROOT_BEEPLAY = "%s.beeplay123.com";
	private static final String URL_ROOT_JDDFUN = "%s.jddfun.com";
	private static String[] URL_SERVERS = null;
	static{
		URL_SERVERS = addAllArray(createArray(URL_ROOT_BEEPLAY, GlobalValueConfig.getConfig("host.url.beeplay").split(",")), createArray(URL_ROOT_JDDFUN, GlobalValueConfig.getConfig("host.url.jddfun").split(",")));
	}
	
	public static String[] getServerUrls(){
		return URL_SERVERS == null ? new String[]{} : URL_SERVERS;
	}

	public static String[] createArray(String text, String[] vals){
		if(vals != null && vals.length > 0){
			String[] arr = new String[vals.length];
			for (int i = 0; i < vals.length; i++) {
				arr[i] = String.format(text, vals[i]);
			}
			return arr;
		}
		return new String[]{};
	}
	
	public static String[] addAllArray(String[] array1, String[] array2){
		return ArrayUtils.addAll(array1, array2);
	}

}
