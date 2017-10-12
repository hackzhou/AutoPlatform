package com.auto.test.core.ui.code;

import java.util.List;
import java.util.concurrent.ExecutorService;
import com.auto.test.common.context.UiContext;
import com.auto.test.common.context.UiThreadPool;
import com.auto.test.core.ui.code.dynamic.DynamicEngine;
import com.auto.test.core.ui.code.result.SuiteResult;
import com.auto.test.entity.UCode;
import com.auto.test.entity.UDevice;
import com.auto.test.entity.UResultSuite;

public class DynaCompileExe {
	
	private ExecutorService cachedThreadPool = null;

	public DynaCompileExe() {
		super();
		this.cachedThreadPool = UiThreadPool.getInstance();;
	}
	
	public void execute(String className, String javaCode, UCode uCode, List<UDevice> devices) {
		if(devices != null && !devices.isEmpty()){
			UResultSuite suite = new SuiteResult().initResultSuite(className);
			for (UDevice uDevice : devices) {
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						UiContext context = new UiContext(className, uCode, uDevice, suite);
						DynamicEngine de = new DynamicEngine();
						de.javaCodeCompileExe(className, javaCode, context);
					}
				});
			}
		}
	}
	
}
