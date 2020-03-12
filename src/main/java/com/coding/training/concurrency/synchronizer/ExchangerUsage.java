package com.coding.training.concurrency.synchronizer;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ExchangerUsage {
	private static Exchanger<String> exchanger = new Exchanger<String>();
	
	public static void main(String[] args){
		new ExchangerThread(exchanger, new AtomicReference<String>("A"), "Thread-A");
		new ExchangerThread(exchanger, new AtomicReference<String>("B"), "Thread-B");
	}
}

class  ExchangerThread implements Runnable {
	private Exchanger<String> exchanger;
	private AtomicReference<String> atomicRef;
	
	public ExchangerThread(Exchanger<String> exchanger, AtomicReference<String> atomicRef, String threadName) {
		this.exchanger = exchanger;
		this.atomicRef = atomicRef;
		new Thread(this, threadName).start();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				atomicRef.set(exchanger.exchange(atomicRef.get()));
				System.out.println(Thread.currentThread().getName() + " : " + atomicRef);
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

