package com.auto.test.socket.pool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ClientThreadPool {
	private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(100);
	
	public static void exec(Runnable runnable){
		executor.scheduleWithFixedDelay(runnable, 200, 200, TimeUnit.MILLISECONDS);
	}
	
	public static void close(){
		executor.shutdown();
	}
	
}
