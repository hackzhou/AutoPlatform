package com.auto.test.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {
	
	public static boolean isEmpty(String str){
		if(str == null || str.isEmpty()){
			return true;
		}
		return false;
	}
	
	public static boolean checkEmail(String email){
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(RULE_EMAIL);
        Matcher m = p.matcher(email);
        return m.matches();
    }
	
	public static String encryptByMD5(String text) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(text.getBytes("UTF8"));
            byte s[] = m.digest();
            return encryptStrBuff(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static String encryptStrBuff(byte[] resultBytes){
		StringBuffer strBuff = new StringBuffer();
        for (int i = 0; i < resultBytes.length; i++) {
            if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1){
            	strBuff.append("0").append(Integer.toHexString(0xFF & resultBytes[i]));
            }else{
            	strBuff.append(Integer.toHexString(0xFF & resultBytes[i]));
            }
        }
		return strBuff.toString();
	}

}
