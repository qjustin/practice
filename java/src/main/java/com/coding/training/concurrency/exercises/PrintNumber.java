package com.coding.training.concurrency.exercises;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 线程1: 1
 * 线程1: 2
 * 线程1: 3
 * 线程1: 4
 * 线程1: 5
 * 
 * 线程2: 6
 * 线程2: 7
 * 线程2: 8
 * 线程2: 9
 * 线程2: 10
 * ... 
 * 线程3: 71
 * 线程3: 72
 * 线程3: 73
 * 线程3: 74
 * 线程3: 75
 */
public class PrintNumber {
	public static void main(String[] args) throws IOException {
		Printer3 printer = new Printer3();

		Thread thread1 = new Thread(() -> {
			try {
				printer.thread1Print();
			} catch (InterruptedException e) { }
		}, "Thread 1");

		Thread thread2 = new Thread(() -> {
			try {
				printer.thread2Print();
			} catch (InterruptedException e) { }
		}, "Thread 2");

		Thread thread3 = new Thread(() -> {
			try {
				printer.thread3Print();
			} catch (InterruptedException e) { }
		}, "Thread 3");

		thread1.start();
		thread2.start();
		thread3.start();
	
		System.in.read();
		
		thread1.interrupt();
		thread2.interrupt();
		thread3.interrupt();
	}

}

class Printer3 extends Thread {
	private int number = 0;
	private int status = 0;

	public void thread1Print() throws InterruptedException {
		while (!Thread.interrupted()) {
			doWork();
			notifyThread2();
			thread1Wait();
		}
	}

	public void thread2Print() throws InterruptedException {
		while (!Thread.interrupted()) {
			thread2Wait();
			doWork();
			notifyThread3();
		}
	}

	public void thread3Print() throws InterruptedException {
		while (!Thread.interrupted()) {
			thread3Wait();
			doWork();
			notifyThread1();
		}
	}

	private synchronized void thread1Wait() throws InterruptedException {
		while (status != 0)
			wait();
	}

	private synchronized void thread2Wait() throws InterruptedException {
		while (status != 1)
			wait();
	}

	private synchronized void thread3Wait() throws InterruptedException {
		while (status != 2)
			wait();
	}

	private synchronized void notifyThread1() throws InterruptedException {
		status = 0;
		notifyAll();
	}

	private synchronized void notifyThread2() throws InterruptedException {
		status = 1;
		notifyAll();
	}

	private synchronized void notifyThread3() throws InterruptedException {
		status = 2;
		notifyAll();
	}

	private void doWork() throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			number++;
			System.out.println(Thread.currentThread().getName() + " : " + number);
			TimeUnit.MILLISECONDS.sleep(300);
		}
	}
}
