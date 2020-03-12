package com.coding.training.concurrency.exercises;

import java.util.concurrent.TimeUnit;


public class MainThreadSubThread {
	public static boolean flag = false;
	private static final Object syncObj = new Object();
	private static int loopCount = 50;
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			while (!Thread.interrupted()) {
				synchronized (syncObj) {
					if (!flag) {
						try {
							System.out.print("SubThread:");
							for (int i = 0; i < 10; i++) {
								System.out.print(i + " ");
							}
							System.out.println();
							flag = true;
							syncObj.notifyAll();
							while (flag)
								syncObj.wait();
						} catch (InterruptedException e) {
						}
					}
				}
			}
		});
		thread.setDaemon(true);
		thread.start();

		for (int i = 0; i < loopCount; i++) {
			synchronized (syncObj) {
				try {
					while (!flag)
						syncObj.wait();
				} catch (InterruptedException e) { }

				System.out.print("MainThread:");
				for (int j = 0; j < 100; j++) 
					System.out.print(j + " ");
				System.out.println();
				System.out.println(i + 1 + " Completed.");
				try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { }

				if (i == loopCount - 1) {
					thread.interrupt();
				} else {
					flag = false;
					syncObj.notifyAll();
				}
			}
		}
	}
}
