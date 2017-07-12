package com.auto.test.core.ui.dynamic;

import java.io.IOException;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ForwardingJavaFileManager;

@SuppressWarnings("rawtypes")
public class ClassFileManager extends ForwardingJavaFileManager{
	
	private JavaClassObject jclassObject;

	@SuppressWarnings("unchecked")
	protected ClassFileManager(StandardJavaFileManager standardManager) {
		super(standardManager);
	}
	
	@Override
	public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
		jclassObject = new JavaClassObject(className, kind);
		return jclassObject;
	}

	public JavaClassObject getJavaClassObject() {
		return jclassObject;
	}
	
}
