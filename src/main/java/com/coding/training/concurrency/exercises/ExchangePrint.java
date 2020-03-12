package com.coding.training.concurrency.exercises;

import java.util.concurrent.TimeUnit;

/**
 * 分别打印ABABAB wait / notify的使用
 */
public class ExchangePrint {
	static Object lock = new Object();
	static boolean monitor = false;
	static java.util.Random rand = new java.util.Random(47);

	public static void main(String[] args) {
		Thread threadA = new Thread(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {
			}
			while (!Thread.interrupted()) {
				synchronized (lock) {
					System.out.println("A");
					try {
						TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
					} catch (InterruptedException e) {
					}
					monitor = true;
					lock.notify();

					try {
						while(monitor)
							lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread threadB = new Thread(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {
			}

			while(!Thread.interrupted()) {
				synchronized(lock) {
					try {
						while(!monitor)
							lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("B");
					try {
						TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
					} catch (InterruptedException e) {
					}
					monitor = false;
					lock.notify();
				}
			}
		});

		threadA.setDaemon(false);
		threadB.setDaemon(false);

		threadA.start();
		threadB.start();
	}
}

// public class ExchangePrint {
// public static void main(String[] args) {
// Printer printer = new Printer();
// ExecutorService exec = Executors.newCachedThreadPool();
// exec.execute(() -> { printer.PrintA(); });
// exec.execute(() -> { printer.PrintB(); });

// try { TimeUnit.SECONDS.sleep(100); } catch (InterruptedException e1) { }
// }
// }

// class Printer {
// public boolean pass = false;

// public void PrintA() {
// while (true) {
// System.out.println("A");
// try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { }
// notifyPrintB();
// waitPrintB();
// }
// }

// public void PrintB() {
// while (true) {
// waitPrintA();
// System.out.println("B");
// try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { }
// notifyPrintA();
// }
// }

// private synchronized void waitPrintA() {
// try {
// while (!pass)
// wait();
// } catch (InterruptedException e) { }
// }

// private synchronized void waitPrintB() {
// try {
// while (pass)
// wait();
// } catch (InterruptedException e) { }
// }

// private synchronized void notifyPrintA() {
// notifyAll();
// pass = false;
// }

// public synchronized void notifyPrintB() {
// notifyAll();
// pass = true;
// }
// }
