package com.auto.test.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class FileUtil {
	
	public boolean writeFile(InputStream is, String path){
		OutputStream output = null;
		try {
			output = new FileOutputStream(path);
			byte[] buf = new byte[1024];
			int bytesRead;        
			while ((bytesRead = is.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(output != null){
					output.close();
				}
				if(is != null){
					is.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}
	
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
	
	public List<String> readFile(InputStream is){
		BufferedReader reader = null;
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			reader = new BufferedReader(new InputStreamReader(is));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				if(!tempString.trim().isEmpty()){
					list.add(tempString.trim());
				}
			}
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
		return list != null ? list : new ArrayList<String>();
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
