package com.coding.training.concurrency.exercises;

import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者
 */





public class ProducerConsumerBuffer {
	private static LinkedList<String> buffer = new LinkedList<>();
	private static final int BUFFER_SIZE = 100;
	private static Object syncLock = new Object();

	public static void main(String[] args) {
		Thread producer = new Thread(() -> {
			while(!Thread.interrupted()) {
				synchronized(syncLock) {
					while(buffer.size() == BUFFER_SIZE) {
						try {
							syncLock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					String uuid = UUID.randomUUID().toString();
					System.out.println("Thread " + Thread.currentThread().getId() +" add uuid:" + uuid);
					buffer.add(uuid);
					syncLock.notifyAll();
				}
			}
		});	

		Thread consumer = new Thread(() -> {
			while(!Thread.interrupted()) {
				synchronized(syncLock) {
					while(buffer.size() == 0) {
						try {
							syncLock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
			 
					String uuid = buffer.poll();
					System.out.println("Thread " + Thread.currentThread().getId() +" poll uuid:" + uuid);
					syncLock.notifyAll();
				}
			}
		});	
		
		producer.setDaemon(false);
		consumer.setDaemon(false);

		producer.start();
		consumer.start();
	}
}

// public class ProducerConsumerBuffer {
// 	private static LinkedList<String> buffer = new LinkedList<>();
// 	private static final int BUFFER_SIZE = 10;
// 	private static Object syncLock = new Object();

// 	public static void main(String[] args) {
		
// 		ExecutorService exec = Executors.newCachedThreadPool();
// 		exec.execute(new Producer(buffer, BUFFER_SIZE, syncLock));
// 		exec.execute(new Producer(buffer, BUFFER_SIZE, syncLock));
		
// 		exec.execute(new Consumer(buffer, syncLock));
// 		exec.execute(new Consumer(buffer, syncLock));
// 		exec.execute(new Consumer(buffer, syncLock));
// 		exec.execute(new Consumer(buffer, syncLock));
		
// 		try {
// 			TimeUnit.SECONDS.sleep(600);
// 		} catch (InterruptedException e) {
// 		}
// 	}
// }

// class Producer implements Runnable {
// 	private LinkedList<String> buffer;
// 	private int bufferSize;
// 	private Object syncLock;
// 	private Random rand = new Random(47);
// 	public Producer(LinkedList<String> buffer, int bufferSize, Object syncLock) {
// 		this.buffer = buffer;
// 		this.bufferSize = bufferSize;
// 		this.syncLock = syncLock;
// 	}

// 	@Override
// 	public void run() {
// 		while (!Thread.interrupted()) {
// 			try {
// 				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
// 			} catch (InterruptedException e) {
// 			}

// 			synchronized (syncLock) {
// 				while (buffer.size() == bufferSize) {
// 					try {
// 						syncLock.wait();
// 					} catch (InterruptedException e) {
// 					}
// 				}
				
// 				String uuid = UUID.randomUUID().toString();
// 				buffer.add(uuid);
// 				System.out.println(String.format("[Producer Thread:%s] \tCreate new UUID:%s Current buffer size:%s",
// 						Thread.currentThread().getId(), uuid, buffer.size()));

// 				syncLock.notifyAll();
// 			}
// 		}
// 	}
// }

// class Consumer implements Runnable {
// 	private LinkedList<String> buffer;
// 	private Object syncLock;
// 	private Random rand = new Random(47);
// 	public Consumer(LinkedList<String> buffer, Object syncLock) {
// 		this.buffer = buffer;
// 		this.syncLock = syncLock;
// 	}

// 	@Override
// 	public void run() {
// 		while (!Thread.interrupted()) {
// 			try {
// 				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
// 			} catch (InterruptedException e) {
// 			}
// 			synchronized (syncLock) {
// 				while (buffer.size() == 0) {
// 					try {
// 						syncLock.wait();
// 					} catch (InterruptedException e) {
// 					}
// 				}

// 				System.out.println(String.format("[Consumer Thread:%s] \tpoll UUID:%s Current buffer size:%s",
// 						Thread.currentThread().getId(), buffer.poll(), buffer.size()));

// 				syncLock.notifyAll();
// 			}
// 		}
// 	}
// }
