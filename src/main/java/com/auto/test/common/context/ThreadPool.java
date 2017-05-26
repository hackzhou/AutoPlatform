package com.auto.test.common.context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	private static ExecutorService cachedThreadPool = null;
	
	public ThreadPool(){
	}

	public static ExecutorService getInstance(){
		if(cachedThreadPool == null){
			cachedThreadPool = Executors.newCachedThreadPool();
		}
		return cachedThreadPool;
	}
}
