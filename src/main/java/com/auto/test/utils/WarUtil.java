package com.auto.test.utils;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import com.auto.test.common.constant.Const;
import com.auto.test.common.exception.BusinessException;

public class WarUtil {
	
	public static void main(String[] args) {
		WarUtil war = new WarUtil();
		System.out.println(war.compareZip("(app)application.properties", "api_app.war"));
	}
	
	public boolean compareZip(String svn, String warName){
		String svnText = new SvnUtil().getSvnFile(svn);
		Properties p1 = getProperties(svnText);
		Properties p2 = getProperties(getWarFile(Const.PATH_FILE_WAR + File.separator + warName));
		boolean bool = isSetEqual(p1.keySet(), p2.keySet());
		if(bool){
			if(writeFile(Const.PATH_FILE_PROPERTIES, svnText)){
				zip(Const.PATH_FILE + File.separator + warName, Const.PATH_FILE_TEMP);
			}
		}
		return bool;
	}
	
	private boolean isSetEqual(Set<?> set1, Set<?> set2) {
		if(set1 == null || set2 == null || set1.isEmpty() || set2.isEmpty() || set1.size() < set2.size()) {
			return false;
		}
		boolean isFullEqual = true;
		Iterator<?> ite2 = set2.iterator();
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
		new FileUtil().deleteDir(Const.PATH_FILE_TEMP);
		if(unzip(warPath)){
			return readFile(Const.PATH_FILE_PROPERTIES);
		}
		return null;
	}
	
	private synchronized boolean unzip(String warPath) {
		BufferedInputStream bis = null;
		ArchiveInputStream in = null;
		try {
			File warFile = new File(warPath);
			bis = new BufferedInputStream(new FileInputStream(warFile));
			in = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.JAR, bis);
			JarArchiveEntry entry = null;
			while ((entry = (JarArchiveEntry) in.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    new File(Const.PATH_FILE_TEMP, entry.getName()).mkdirs();
                } else {
                	OutputStream out = null;
                	try {
                		out = FileUtils.openOutputStream(new File(Const.PATH_FILE_TEMP, entry.getName()));
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
	
	private synchronized boolean zip(String destFile, String zipDir) {
		BufferedOutputStream bos = null;
		ArchiveOutputStream out = null;
		try {
			File outFile = new File(destFile);
			outFile.createNewFile();
			bos = new BufferedOutputStream(new FileOutputStream(outFile));
			out = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.JAR, bos);
			if (zipDir.charAt(zipDir.length() - 1) != '/') {
                zipDir += '/';
            }
			Iterator<File> files = FileUtils.iterateFiles(new File(zipDir), null, true);
			while (files.hasNext()) {
                File file = files.next();
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getPath().replace(zipDir.replace("/", "\\"), ""));
                out.putArchiveEntry(zipArchiveEntry);
                IOUtils.copy(new FileInputStream(file), out);
                out.closeArchiveEntry();
            }
			out.finish();
			return true;
		} catch (IOException e1) {
			throw new BusinessException("创建文件失败[" + destFile + "][" + e1.getMessage() + "]");
		} catch (ArchiveException e2) {
			throw new BusinessException("不支持的压缩格式[" + destFile + "][" + e2.getMessage() + "]");
		} finally {
			try {
        		if(out != null){
        			out.close();
        		}
        		if(bos != null){
        			bos.close();
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
	
	public boolean writeFile(String fullName, String source){
		BufferedWriter bw = null;
		try {
			File file = new File(fullName);
			if(!file.exists()){
				file.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(fullName));
			bw.write(source);
			bw.flush();
			return true;
		} catch (Exception e) {
			throw new BusinessException("svn的application.properties文件内容写入war失败[" + e.getMessage() + "]");
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
