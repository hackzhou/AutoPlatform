package com.auto.test.common.context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	
	private static class SingletonFactory {
		private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	}
	
	private ThreadPool(){
	}

	public static ExecutorService getInstance(){
		return SingletonFactory.cachedThreadPool;
	}
}
