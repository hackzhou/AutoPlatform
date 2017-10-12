package com.auto.test.core.ui.code.dynamic;

import java.net.URI;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;

public class JavaClassObject extends SimpleJavaFileObject {

	protected final ByteArrayOutputStream bos = new ByteArrayOutputStream();

	public JavaClassObject(String name, JavaFileObject.Kind kind) {
		super(URI.create("string:///" + name.replace('.', '/') + kind.extension), kind);
	}

	public byte[] getBytes() {
		return bos.toByteArray();
	}

	@Override
	public OutputStream openOutputStream() throws IOException {
		return bos;
	}
}