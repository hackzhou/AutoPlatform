package com.auto.test.common.context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UiThreadPool {
	
	private static class SingletonFactory {
		private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	}
	
	private UiThreadPool(){
		super();
	}

	public static ExecutorService getInstance(){
		return SingletonFactory.cachedThreadPool;
	}
}
