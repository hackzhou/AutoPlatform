package com.auto.test.core.ui.code.dynamic;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auto.test.common.constant.Const;
import com.auto.test.common.context.UiContext;
import com.auto.test.utils.FileUtil;
import javax.tools.JavaFileObject;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;

public class DynamicEngine {
	private Logger logger = LoggerFactory.getLogger(DynamicEngine.class);
	private String classpath;
	private URLClassLoader parentClassLoader;
	
	public DynamicEngine() {
		super();
		this.parentClassLoader = (URLClassLoader) this.getClass().getClassLoader();
		this.buildClassPath();
	}
	
	private void buildClassPath() {
		this.classpath = null;
		StringBuffer sb = new StringBuffer();
		for (URL url : this.parentClassLoader.getURLs()) {
			String p = url.getFile();
			sb.append(p).append(File.pathSeparator);
		}
		this.classpath = sb.toString();
	}
	
	public Object javaCodeCompileExe(String fullClassName, String javaCode, UiContext context) {
		Object instance = null;
		ClassFileManager fileManager = null;
		DynamicClassLoader dynamicClassLoader = null;
		try {
			String path = Const.PATH_UI_CODE + File.separator + fullClassName;
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
			fileManager = new ClassFileManager(compiler.getStandardFileManager(diagnostics, null, null));
			List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
			jfiles.add(new CharSequenceJavaFileObject(fullClassName, javaCode));
			List<String> options = new ArrayList<String>();
			options.add("-encoding");
			options.add("UTF-8");
			options.add("-classpath");
			options.add(this.classpath);
			JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, jfiles);
			boolean success = task.call();
			if (success) {
				JavaClassObject jco = fileManager.getJavaClassObject();
				dynamicClassLoader = new DynamicClassLoader(this.parentClassLoader);
				Class<?> clazz = dynamicClassLoader.loadClass(fullClassName, jco);
				instance = clazz.newInstance();
				//Method m = clazz.getMethod("main", String[].class);
				//m.invoke(instance, (Object) new String[]{});
				Field field = clazz.getDeclaredField("context");
				field.setAccessible(true);
				field.set(instance, context);
				Method m = clazz.getMethod("execute");
				m.invoke(instance);
				Field fieldLog = clazz.getDeclaredField("log");
				fieldLog.setAccessible(true);
				new FileUtil().writeJavaFile(path, getCurrentDate() + "_success.log", fieldLog.get(instance).toString());
			}else{
				StringBuffer error = new StringBuffer();
				for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
					error.append(compilePrint(diagnostic));
				}
				new FileUtil().writeJavaFile(path, getCurrentDate() + "_error.log", error.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(dynamicClassLoader != null){
					dynamicClassLoader.close();
				}
				if(fileManager != null){
					fileManager.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		return instance;
	}
	
	private String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("'A'yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	
	private String compilePrint(Diagnostic<?> diagnostic) {
		StringBuffer res = new StringBuffer();
		res.append("Code:[" + diagnostic.getCode() + "]\r\n");
		res.append("Kind:[" + diagnostic.getKind() + "]\r\n");
		res.append("Position:[" + diagnostic.getPosition() + "]\r\n");
		res.append("Start Position:[" + diagnostic.getStartPosition() + "]\r\n");
		res.append("End Position:[" + diagnostic.getEndPosition() + "]\r\n");
		res.append("Source:[" + diagnostic.getSource() + "]\r\n");
		res.append("Message:[" + diagnostic.getMessage(null) + "]\r\n");
		res.append("LineNumber:[" + diagnostic.getLineNumber() + "]\r\n");
		res.append("ColumnNumber:[" + diagnostic.getColumnNumber() + "]\r\n");
		logger.error(res.toString());
		return res.toString();
	}
	
}
