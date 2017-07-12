package com.auto.test.core.ui;

import java.util.concurrent.ExecutorService;
import com.auto.test.common.context.UiThreadPool;
import com.auto.test.core.ui.dynamic.DynamicEngine;

public class DynaCompileExe {
	
	private ExecutorService cachedThreadPool = null;

	public DynaCompileExe() {
		super();
		this.cachedThreadPool = UiThreadPool.getInstance();;
	}
	
	public void execute(String className, String javaCode) {
		cachedThreadPool.execute(new Runnable() {

			@Override
			public void run() {
				DynamicEngine de = new DynamicEngine();
				de.javaCodeCompileExe(className, javaCode);
			}
			
		});
	}

}
