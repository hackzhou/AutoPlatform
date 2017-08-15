package com.auto.test.utils;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import com.auto.test.common.exception.BusinessException;

public class SvnUtil {
	private static final String SVN_LKCZ_QA = "https://61.155.136.217:8443/svn/LKCZ/QA/application_test/";
	private static final String SVN_NAME = "zhouzhou";
	private static final String SVN_PASSWORD = "Jih3wroK1d19yerM";
	private SVNRepository repository = null;
	
	public SvnUtil() {
		super();
		init();
	}
	
	public static void main(String[] args) {
		System.out.println(new SvnUtil().getSvnFile("(app)application.properties"));
	}
	
	@SuppressWarnings("deprecation")
	private void init() {
		try {
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(SVN_LKCZ_QA));
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(SVN_NAME, SVN_PASSWORD);
			repository.setAuthenticationManager(authManager);
		} catch (SVNException e) {
			throw new BusinessException("实例化SVN服务器失败[" + e.getMessage() + "]");
		}
	}
	
	public String getSvnFile(String fileName){
		SVNProperties svnProperties = null;
		ByteArrayOutputStream baos = null;
		try {
			svnProperties = new SVNProperties();
			baos = new ByteArrayOutputStream();
			repository.getFile(fileName, -1, svnProperties, baos);
			return baos.toString();
		} catch (SVNException e) {
			throw new BusinessException("获取SVN服务器文件内容失败[" + SVN_LKCZ_QA + fileName + "][" + e.getMessage() + "]");
		} finally {
			try {
				if(svnProperties != null){
					svnProperties.dispose();
				}
				if(baos != null){
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
