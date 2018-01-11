package com.auto.test.utils;

import java.io.IOException;
import java.net.InetAddress;
import com.auto.test.common.exception.BusinessException;

public class CheckUtil {
	
	public void checkIP(String ip, String text) {
		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		if(ip == null || ip.isEmpty()){
			throw new BusinessException(text + "[ip为空]未配置！");
		}
		if(ip.matches(regex)){
			try {
				if(!InetAddress.getByName(ip).isReachable(5000)){
					throw new BusinessException(text + "[ip=" + ip + "]连接超时！");
				}
			} catch (IOException e) {
				throw new BusinessException(text + "[ip=" + ip + "]连接超时！[" + e.getMessage() + "]");
			}
		}else{
			throw new BusinessException(text + "[ip=" + ip + "]格式错误！");
		}
	}
	
}
