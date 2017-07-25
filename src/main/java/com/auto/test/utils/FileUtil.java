package com.auto.test.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	public String getLastLogFile(String path){
		try {
			File dir = new File(path);
			if(dir.exists() && dir.isDirectory()){
				SimpleDateFormat sdf = new SimpleDateFormat("'A'yyyyMMddHHmmss");
				if(dir.list().length > 0){
					Date date = null;
					String endName = "";
					for (String file : dir.list()) {
						if(file.endsWith(".log")){
							String[] nameArr = file.split("_");
							Date d = sdf.parse(nameArr[0]);
							if(date == null || d.getTime() > date.getTime()){
								date = d;
								endName = nameArr[1];
							}
						}
					}
					if(date != null){
						return sdf.format(date) + "_" + endName;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteDir(String path){
		File dir = new File(path);
		return deleteDir(dir);
	}
	
	public boolean deleteDir(File dir){
		try {
			if(dir.exists()){
				if(dir.isDirectory()){
					for (String file : dir.list()) {
						if(!deleteDir(new File(dir, file))){
							return false;
						}
					}
				}
				return dir.delete();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteFile(String path, String name){
		File file = new File(path + File.separator + name);
		return deleteFile(file);
	}
	
	public boolean deleteFile(File file){
		try {
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