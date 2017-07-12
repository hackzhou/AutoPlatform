package com.auto.test.core.ui;

public class DynaCompTest {

	public static void main(String[] args) throws Exception {
		String fullName = "DynaClass";
		StringBuilder src = new StringBuilder();
		src.append("import com.auto.test.core.ui2.MyOther;\n");
		src.append("import com.alibaba.fastjson.JSON;\n");
		src.append("public class DynaClass {\n");
		src.append("    public String toString() {\n");
		src.append("        System.out.println(new MyOther().getOther());\n");
		src.append("        System.out.println(JSON.VERSION);\n");
		src.append("        return \"Hello, I am \" + ");
		src.append("this.getClass().getSimpleName();\n");
		src.append("    }\n");
		src.append("}\n");

		System.out.println(src);
		DynamicEngine de = new DynamicEngine();
		Object instance = de.javaCodeToObject(fullName, src.toString());
		System.out.println(instance);
	}

}
