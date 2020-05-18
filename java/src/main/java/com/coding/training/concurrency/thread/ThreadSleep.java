package com.coding.training.concurrency.thread;

import java.util.concurrent.locks.LockSupport;

public class ThreadSleep {
    public static void main(String[] args) {
        System.out.println("begin - args[0] = " + args[0]);
        try {
            if (args[0].equals("1")) {
                System.out.println("Thread.sleep(0): CPU Usage : 99.9%");
                while (true) {
                    Thread.sleep(0);
                }
            } else if (args[0].equals("2")) {
                System.out.println("Thread.sleep(1): CPU Usage : 0.3 ~ 0.7%");
                while (true) {
                    Thread.sleep(1);
                }
            } else if (args[0].equals("3")) {
                System.out.println("Thread.yield(): CPU Usage : 99.9%");
                while (true) {
                    Thread.yield();
                }
            } else if (args[0].equals("4")) {
                System.out.println("Thread.class.wait(0, 1): CPU Usage : 0.3 ~ 0.7%");
                synchronized (Thread.class) {
                    while (true) {
                        Thread.class.wait(0, 1);
                    }
                }
            } else if (args[0].equals("5")) {
                System.out.println("LockSupport.parkNanos(1): CPU Usage : 6.6% ~ 7.3%");
                while (true) {
                    LockSupport.parkNanos(1);
                }
            }
        } catch (Exception ex) {
            // nothing to do;
        }
    }
}
