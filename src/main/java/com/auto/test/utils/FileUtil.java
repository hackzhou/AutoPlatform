package com.auto.test.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class FileUtil {
	
	public boolean writeJavaFile(String dirPath, String fileName, String source){
		BufferedWriter bw = null;
		try {
			String fullName = dirPath + File.separator + fileName;
			File dir = new File(dirPath);
			if(!dir.exists()){
				dir.mkdirs();
			}
			File file = new File(fullName);
			if(!file.exists()){
				file.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(fullName));
			bw.write(source);
			bw.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(bw != null){
					bw.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public File getFile(String dirPath, String fileName){
		return new File(dirPath + File.separator + fileName);
	}
	
	public String readJavaFile(File file){
		BufferedReader reader = null;
		try {
			StringBuffer sb = new StringBuffer();
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString + "\r\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null){
					reader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean delete(String path, String name){
		try {
			File file = new File(path + File.separator + name);
			if(file.exists()){
				file.delete();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
