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
	
	public static String string2HexString(String strPart) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }
	
	public static String hexString2String(String src) {
        String temp = "";
        for (int i = 0; i < src.length() / 2; i++) {
            temp = temp + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return temp;
    }
	
	public static String authMail(String str){
		if("zhouzhou".equals(str)){
			return StrUtil.hexString2String("637570366d386870");
		}
		return str;
	}
	
}
