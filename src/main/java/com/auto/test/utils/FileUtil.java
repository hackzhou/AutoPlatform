package com.auto.test.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	
	public static void writeJavaFile(String dirPath, String fileName, String source){
		BufferedWriter bw = null;
		try {
			File dir = new File(dirPath);
			if(!dir.exists()){
				dir.mkdirs();
			}
			File file = new File(dirPath + File.separator + fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(dirPath + File.separator + fileName));
			bw.write(source);
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
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
	
}
