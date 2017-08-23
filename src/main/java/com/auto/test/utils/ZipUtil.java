package com.auto.test.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.exception.BusinessException;

public class ZipUtil {
	private static Logger logger = LoggerFactory.getLogger(ZipUtil.class);
	
	public static void main(String[] args) {
		zip("C:\\Users\\Administrator\\Desktop\\testa\\s.zip", "C:\\Users\\Administrator\\Desktop\\testa\\v3.0.0.1");
		unzip("C:\\Users\\Administrator\\Desktop\\testa\\s.zip", "C:\\Users\\Administrator\\Desktop\\testa\\s");
	}
	
	private static synchronized boolean zip(String destFile, String zipDir) {
		BufferedOutputStream bos = null;
		ArchiveOutputStream out = null;
		try {
			File outFile = new File(destFile);
			outFile.createNewFile();
			bos = new BufferedOutputStream(new FileOutputStream(outFile));
			out = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP, bos);
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
			logger.error("压缩时创建文件失败[" + destFile + "][" + e1.getMessage() + "]");
			throw new BusinessException("压缩时创建文件失败[" + destFile + "][" + e1.getMessage() + "]");
		} catch (ArchiveException e2) {
			logger.error("压缩时不支持的压缩格式[" + destFile + "][" + e2.getMessage() + "]");
			throw new BusinessException("压缩时不支持的压缩格式[" + destFile + "][" + e2.getMessage() + "]");
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
	
	private static synchronized boolean unzip(String zipPath, String destPath) {
		BufferedInputStream bis = null;
		ArchiveInputStream in = null;
		try {
			File zipFile = new File(zipPath);
			bis = new BufferedInputStream(new FileInputStream(zipFile));
			in = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.ZIP, bis);
			ZipArchiveEntry entry = null;
			while ((entry = (ZipArchiveEntry) in.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    new File(destPath, entry.getName()).mkdirs();
                } else {
                	OutputStream out = null;
                	try {
                		out = FileUtils.openOutputStream(new File(destPath, entry.getName()));
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
			logger.error("解压时未找到zip文件[" + zipPath + "][" + e1.getMessage() + "]");
			throw new BusinessException("解压时未找到zip文件[" + zipPath + "][" + e1.getMessage() + "]");
        } catch (ArchiveException e2) {
        	logger.error("解压时不支持的压缩格式[" + zipPath + "][" + e2.getMessage() + "]");
        	throw new BusinessException("解压时不支持的压缩格式[" + zipPath + "][" + e2.getMessage() + "]");
        } catch (IOException e3) {
        	logger.error("解压时文件写入发生错误[" + zipPath + "][" + e3.getMessage() + "]");
        	throw new BusinessException("解压时文件写入发生错误[" + zipPath + "][" + e3.getMessage() + "]");
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
	
}
