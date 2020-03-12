package com.coding.training.concurrency.thread;

public class DaemonThread {
    public static void main(String[] args) {

       Thread t = new Thread(() -> {
           System.out.println("Daemon thread start");
           try {
               Thread.sleep(1000 * 10);
           } catch (Exception ex) {
               ex.printStackTrace();
           }
           System.out.println("Daemon thread end");
       });

        t.setDaemon(false);
       t.start();

        System.out.println("Main thread start");
        try {
            Thread.sleep(1000 * 2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Main thread end");
    }
}
