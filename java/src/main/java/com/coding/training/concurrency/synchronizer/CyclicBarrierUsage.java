package com.coding.training.concurrency.synchronizer;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用
 *
 */
public class CyclicBarrierUsage {
	public static void main(String[] args) throws IOException {

		CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
			@Override
			public void run() {
				System.out.println("CyclicBarrier run method:" + Thread.currentThread().getName());
			}
		});

		CyclicBarrierDemo barrierDemo = new CyclicBarrierDemo(barrier);
		new Thread(barrierDemo, "thread-01").start();
		new Thread(barrierDemo, "thread-02").start();
		
		System.in.read();
	}
}

class CyclicBarrierDemo implements Runnable {
	private CyclicBarrier barrier;

	public CyclicBarrierDemo(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + " execute completed.");
				barrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
