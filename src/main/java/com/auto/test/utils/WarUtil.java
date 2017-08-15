package com.auto.test.utils;

import java.io.File;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import com.auto.test.common.constant.Const;
import com.auto.test.common.exception.BusinessException;

public class WarUtil {
	
	public static void main(String[] args) {
		WarUtil war = new WarUtil();
		System.out.println(war.compareProp("(app)application.properties", "D:\\test\\api_app.war"));
	}
	
	public boolean compareProp(String name, String war){
		Properties p1 = getProperties(new SvnUtil().getSvnFile(name));
		Properties p2 = getProperties(getWarFile(war));
		return isSetEqual(p1.keySet(), p2.keySet());
	}
	
	private boolean isSetEqual(Set<?> set1, Set<?> set2) {
		if(set1 == null && set2 == null) {
			return false;
		}
		if(set1 == null || set2 == null || set1.size() != set2.size() || set1.size() == 0 || set2.size() == 0) {
			return false;
		}
		Iterator<?> ite2 = set2.iterator();
		boolean isFullEqual = true;
		while (ite2.hasNext()) {
			if (!set1.contains(ite2.next())){
				isFullEqual = false;
			}
		}
		return isFullEqual;
	}
	
	private Properties getProperties(String text){
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(text.getBytes());
			Properties pps = new Properties();
			pps.load(is);
			return pps;
		} catch (IOException e) {
			throw new BusinessException("properties文件解析失败[" + e.getMessage() + "]");
		} finally {
			try {
        		if(is != null){
        			is.close();
        		}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public String getWarFile(String warPath){
		new FileUtil().deleteDir(Const.PATH_WAR_TEMP);
		if(unzip(warPath)){
			return readFile(Const.PATH_WAR_PROPERTIES);
		}
		return null;
	}
	
	private boolean unzip(String warPath) {
		BufferedInputStream bis = null;
		ArchiveInputStream in = null;
		try {
			File warFile = new File(warPath);
			bis = new BufferedInputStream(new FileInputStream(warFile));
			in = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.JAR, bis);
			JarArchiveEntry entry = null;
			while ((entry = (JarArchiveEntry) in.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    new File(Const.PATH_WAR_TEMP, entry.getName()).mkdir();
                } else {
                	OutputStream out = null;
                	try {
                		out = FileUtils.openOutputStream(new File(Const.PATH_WAR_TEMP, entry.getName()));
                        IOUtils.copy(in, out);
					} finally {
						if(out != null){
							out.close();
						}
					}
                }
            }
			return true;
		} catch (FileNotFoundException e1) {
			throw new BusinessException("未找到war文件[" + warPath + "][" + e1.getMessage() + "]");
        } catch (ArchiveException e2) {
        	throw new BusinessException("不支持的压缩格式[" + warPath + "][" + e2.getMessage() + "]");
        } catch (IOException e3) {
        	throw new BusinessException("文件写入发生错误[" + warPath + "][" + e3.getMessage() + "]");
        } finally {
        	try {
        		if(in != null){
        			in.close();
        		}
        		if(bis != null){
        			bis.close();
        		}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String readFile(String path){
		File file = new File(path);
		if(file.exists() && file.isFile()){
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
		}else{
			throw new BusinessException("未找到application.properties文件[" + path + "]");
		}
		return null;
	}

}
