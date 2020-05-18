package com.coding.training.concurrency.thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class HandlerThreadException {

	public static void main(String[] args) {
		new Thread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {	
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("caught " + e);
			}
		});
		
		ExecutorService exec = Executors.newCachedThreadPool(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
					@Override
					public void uncaughtException(Thread t, Throwable e) {
						 System.out.println("caught " + e);
					}
				});
				
				return t;
			}
		});
		
		exec.shutdown();
	}
}
