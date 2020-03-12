package com.coding.training.concurrency.exercises;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 
2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 
3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 
4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 
5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 
6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 
7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 
8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 
9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 
 * */

public class ConcurrentWriteFile {
	public static void main(String[] args) {	
		new FileWriter(new StringBuilder(), "1");
		new FileWriter(new StringBuilder(), "2");
		new FileWriter(new StringBuilder(), "3");
		new FileWriter(new StringBuilder(), "4");
		new FileWriter(new StringBuilder(), "5");
		new FileWriter(new StringBuilder(), "6");
		new FileWriter(new StringBuilder(), "7");
		new FileWriter(new StringBuilder(), "8");
		new FileWriter(new StringBuilder(), "9");
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter.PrintResult();
	}
}

class FileWriter extends Thread {
	private static final Map<String, StringBuilder> hashMap = new HashMap<String, StringBuilder>();
	private static final Object syncLock = new Object();
	private static final int numberOfColumn = 18;
	private static AtomicInteger syncCounter = new AtomicInteger(0);
	private static AtomicInteger numberOfFiles = new AtomicInteger(0);

	public FileWriter(StringBuilder sb, String threadName) {
		super(threadName);
		hashMap.put(threadName, sb);
		syncCounter.incrementAndGet();
		numberOfFiles.incrementAndGet();
		start();
	}

	@Override
	public void run() {
		for (int i = 0; i < numberOfColumn; i++) {

			int threadNumber = Integer.valueOf(Thread.currentThread().getName());
			int	numberOfFile = numberOfFiles.get();
			int key = (Math.abs(i %  numberOfFile - numberOfFile) + threadNumber) % numberOfFile;
			key = key == 0 ? numberOfFile : key;
			hashMap.get(String.valueOf(key)).append(threadNumber + " ");
			
			synchronized(syncLock) {

				if (syncCounter.decrementAndGet() == 0) {

					syncCounter.set(numberOfFiles.get());

					syncLock.notifyAll();
				} else {
					try {
						syncLock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void PrintResult() {
		for(int i = 1; i <= numberOfFiles.get(); i++) {
			System.out.println(hashMap.get(String.valueOf(i)).toString());
		}
	}
}
