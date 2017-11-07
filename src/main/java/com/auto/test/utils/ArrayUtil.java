package com.auto.test.utils;

import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class ArrayUtil {
	private static final String URL_ROOT_TEST = "http://%s.beeplay123.com";
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(getAllIP()));
	}
	
	public static String[] getAllIP(){
//		String[] a = createArray(Const.IP_SERVER_1, new String[]{"220", "229", "209", "211", "217", "214", "213", "215", "221", "216", "219", "222", "218", "224", "228"});
//		String[] b = createArray(Const.IP_SERVER_2, new String[]{"208", "209", "219", "127", "150", "161", "230", "238", "232", "236", "237", "239", "241", "242", "243", "162", "163"});
//		return addAllArray(b, a);
		return createArray(new String[]{"uic-api", "trans-api", "platform-api", "shop-api", "ops-api", "open-api", "quoits-api", "data-api", "admin", "game-api", "www"});
	}
	
	public static String[] createArray(String[] vals){
		if(vals != null && vals.length > 0){
			String[] arr = new String[vals.length];
			for (int i = 0; i < vals.length; i++) {
				arr[i] = String.format(URL_ROOT_TEST, vals[i]);
			}
			return arr;
		}
		return new String[]{};
	}
	
	public static String[] createArray(String text, String[] vals){
		if(vals != null && vals.length > 0){
			String[] arr = new String[vals.length];
			for (int i = 0; i < vals.length; i++) {
				arr[i] = text + "." + vals[i];
			}
			return arr;
		}
		return new String[]{};
	}
	
	public static String[] addAllArray(String[] array1, String[] array2){
		return ArrayUtils.addAll(array1, array2);
	}

}
