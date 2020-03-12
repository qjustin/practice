package com.coding.training.concurrency.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class PrintABC {
	private static Printer printer = new Printer();

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		exec.execute(() ->{ try {printer.PrintA();} catch (InterruptedException e) {}});
		exec.execute(() ->{ try {printer.PrintB();} catch (InterruptedException e) {}});
		exec.execute(() ->{ try {printer.PrintC();} catch (InterruptedException e) {}});
		
		try {
			TimeUnit.SECONDS.sleep(60);
			exec.shutdownNow();
		} catch (InterruptedException e) {}
	}


	static class Printer {
		private int status = 0;

		public void PrintA() throws InterruptedException {
			while (!Thread.interrupted()) {
				System.out.println("A");
				TimeUnit.MILLISECONDS.sleep(500);
				notifyB();
				waitA();
			}
		}

		public void PrintB() throws InterruptedException {
			while (!Thread.interrupted()) {
				waitB();
				System.out.println("B");
				TimeUnit.MILLISECONDS.sleep(500);
				notifyC();
			}
		}

		public void PrintC() throws InterruptedException {
			while (!Thread.interrupted()) {
				waitC();
				System.out.println("C");
				TimeUnit.MILLISECONDS.sleep(500);
				notifyA();
			}
		}

		private synchronized void waitA() throws InterruptedException {
			while (status != 0)
				wait();
		}

		private synchronized void waitB() throws InterruptedException {
			while (status != 1)
				wait();
		}

		private synchronized void waitC() throws InterruptedException {
			while (status != 2)
				wait();
		}

		private synchronized void notifyA() {
			status = 0;
			notifyAll();
		}

		private synchronized void notifyB() {
			status = 1;
			notifyAll();
		}

		private synchronized void notifyC() {
			status = 2;
			notifyAll();
		}
	}
}


