package com.coding.training.concurrency.thread;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class InheritableThreadLocalUsage
{
	/**
	 * 主线程将 itl初始化为0
	 * 
	 * 主线程创建的子线程中读取 itl的值将永远为0， 即使主线程在子线程修改了itl的值之后才创建出新的子线程，该子线程中读取itl的值仍然未0.
	 * 线程初始化InheritableThreadLocal变量或调用set方法后，该变量(值)与该线程绑定，子线程无法修改（调用set只是将新的值与子线程绑定），
	 * */
	static InheritableThreadLocal<Integer> itl = new InheritableThreadLocal<Integer>(){
		@Override
		public Integer initialValue() {
			return 0;
		}
	};
	
    public static void main( String[] args ) throws InterruptedException
    {
    	new Thread(() -> { method01();}).start();
    	TimeUnit.MILLISECONDS.sleep(100);
    	new Thread(() -> { method01();}).start();
    	TimeUnit.MILLISECONDS.sleep(100);
    	new Thread(() -> { method01();}).start();
    	
    	
    	new Thread(() -> { method02();}).start();
    	TimeUnit.MILLISECONDS.sleep(100);
    	new Thread(() -> { method02();}).start();
    	TimeUnit.MILLISECONDS.sleep(100);
    	new Thread(() -> { method02();}).start();
    }
    
    private static void method01() {
    	System.out.println("Method1:" + itl.get());
    	itl.set(itl.get() + 1);
    	
    	new Thread(() -> { method02();}).start();
    	
    	try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	new Thread(() -> { method02();}).start();
    	
    	try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	new Thread(() -> { method02();}).start();
    }
    
    private static void method02() {
    	System.out.println("Method2:" + itl.get());
    	itl.set(itl.get() + 2);
    	
    	new Thread(() -> { method03();}).start();
    	
    	try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	new Thread(() -> { method03();}).start();
    	
    	try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	new Thread(() -> { method03();}).start();
    }
    
    private static void method03() {
    	System.out.println("Method3:" + itl.get());
    	itl.set(itl.get() + 3);
    }
}
