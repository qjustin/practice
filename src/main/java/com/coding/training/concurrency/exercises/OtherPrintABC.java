package com.coding.training.concurrency.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 循环打印ABC
 */
public class OtherPrintABC {
	static Object lock = new Object();
	static int status = 1;
	static java.util.Random rand = new java.util.Random(47);
	public static void main(final String[] args) {
		Thread t1 = new Thread(() -> {
			while(!Thread.interrupted()) {
				synchronized(lock) {
					System.out.println("A");
					try {
						TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
					} catch (InterruptedException e) {
					}
					status = 2;
					lock.notifyAll();
					while(status != 1) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});

		Thread t2 = new Thread(() -> {
			while(!Thread.interrupted()) {
				synchronized(lock) {
					while(status != 2) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("B");
					try {
						TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
					} catch (InterruptedException e) {
					}
					status = 3;
					lock.notifyAll();
				}
			}
		});

		Thread t3 = new Thread(() -> {
			while(!Thread.interrupted()) {
				synchronized(lock) {
					while(status != 3) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("C");
					try {
						TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
					} catch (InterruptedException e) {
					}
					status = 1;
					lock.notifyAll();
				}
			}
		});

		t1.setDaemon(false);
		t2.setDaemon(false);
		t3.setDaemon(false);

		t1.start();
		t2.start();
		t3.start();
	}
	// public static void main(String[] args) {
	// 	ExecutorService exec = Executors.newCachedThreadPool();
	// 	Printer printer = new Printer(exec, 5);
	// 	printer.StartPrint();
	// }

	// static class Printer {
	// 	private int status = 0;
	// 	private int printCount = 100;
	// 	private ExecutorService exec = null;

	// 	Printer() {}
	// 	Printer(ExecutorService exec, int printCount) {
	// 		this.exec = exec;
	// 		this.printCount = printCount;
	// 	}

	// 	public void StartPrint() {
	// 		exec.execute(() -> { try {PrintA();} catch (InterruptedException e) {}});
	// 		exec.execute(() -> { try {PrintB();} catch (InterruptedException e) {}});
	// 		exec.execute(() -> { try {PrintC(exec);} catch (InterruptedException e) {}});
	// 	}

	// 	public void StopPrint() {
	// 		exec.shutdownNow();
	// 	}

	// 	public void PrintA() throws InterruptedException {
	// 		while (!Thread.interrupted()) {
	// 			System.out.println("A");
	// 			TimeUnit.MILLISECONDS.sleep(500);
	// 			notifyB();
	// 			waitA();
	// 		}
	// 	}

	// 	public void PrintB() throws InterruptedException {
	// 		while (!Thread.interrupted()) {
	// 			waitB();
	// 			System.out.println("B");
	// 			TimeUnit.MILLISECONDS.sleep(500);
	// 			notifyC();
	// 		}
	// 	}

	// 	public void PrintC(ExecutorService exec) throws InterruptedException {
	// 		while (!Thread.interrupted()) {
	// 			waitC();
	// 			System.out.println("C");
	// 			TimeUnit.MILLISECONDS.sleep(500);
	// 			if (printCount == 1)
	// 				exec.shutdownNow();
	// 			notifyA();
	// 			printCount--;
	// 		}
	// 	}

	// 	private synchronized void waitA() throws InterruptedException {
	// 		while (status != 0)
	// 			wait();
	// 	}

	// 	private synchronized void waitB() throws InterruptedException {
	// 		while (status != 1)
	// 			wait();
	// 	}

	// 	private synchronized void waitC() throws InterruptedException {
	// 		while (status != 2)
	// 			wait();
	// 	}

	// 	private synchronized void notifyA() {
	// 		status = 0;
	// 		notifyAll();
	// 	}

	// 	private synchronized void notifyB() {
	// 		status = 1;
	// 		notifyAll();
	// 	}

	// 	private synchronized void notifyC() {
	// 		status = 2;
	// 		notifyAll();
	// 	}
	// }
}


