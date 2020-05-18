package com.coding.training.concurrency.thread;

public class ThreadVariant {
	public static void main(String[] args) {
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName());
		}, "thread-01").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		}, "thread-02").start();
		
		new Thread(new Thread() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		}, "thread-03").start();
		
		new Thread("thread-04") {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		}.start();
		
		new ThreadImpl("thread-05");
		new RunnableImpl("thread-06");
	}
}

class ThreadImpl extends Thread {
	public ThreadImpl(String threadName) {
		super(threadName);
		start();
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}

class RunnableImpl implements Runnable {
	public RunnableImpl(String threadName) {
		new Thread(this, threadName).start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName());
	}
}
