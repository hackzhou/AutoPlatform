package com.auto.test.core.ui;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.JavaCompiler.CompilationTask;
import com.auto.test.common.constant.Const;
import com.auto.test.common.exception.BusinessException;

public class DynamicCompileExecute {
	
	public static final String UI_CODE_PATH = System.getProperty("user.home") + File.separator + Const.AUTO_PLATFORM + File.separator + Const.UI_CODE_TEMP;

	public static boolean compile(File[] files) {
		StandardJavaFileManager javaFileManager = null;
		try {
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			javaFileManager = javaCompiler.getStandardFileManager(null, null, null);
			Iterable<? extends JavaFileObject> it = javaFileManager.getJavaFileObjects(files);
			CompilationTask task = javaCompiler.getTask(null, javaFileManager, null, Arrays.asList("-d", UI_CODE_PATH), null, it);
			if (task.call()) {
				for (File file : files) {
					System.out.println(file);
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (javaFileManager != null) {
					javaFileManager.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void execute(String packageName, String className, String methodName) {
		try {
			FileSystemClassLoader fscl = new FileSystemClassLoader(UI_CODE_PATH);
			Class<?> cls = fscl.findClass(packageName + "." + className);
			Method method = cls.getDeclaredMethod(methodName);
			Object obj = cls.newInstance();
	        method.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static File[] getFiles(String dirName, String[] fileNames){
		dirName = UI_CODE_PATH + File.separator + dirName;
		File fileDir = new File(dirName);
		if(!fileDir.exists() && !fileDir.isDirectory()){
			throw new BusinessException("文件夹不存在[" + dirName + "]");
		}
		if(fileNames != null && fileNames.length > 0){
			File[] files = new File[fileNames.length];
			for (int i = 0; i < fileNames.length; i++) {
				File file = new File(dirName + File.separator + fileNames[i]);
				if(file.exists()){
					files[i] = file;
				}else{
					throw new BusinessException("文件不存在[" + dirName + File.separator + fileNames[i] + "]");
				}
			}
			return files;
		}
		return null;
	}

}
