package com.coding.training.algorithmic.thread;

/**
 * https://www.cnblogs.com/xiaoxi/p/8035725.html
 *
 * 三个线程ABC
 * 第一个线程打印A
 * 第二个线程打印B
 * 第三个线程打印C
 * <p>
 * 输出：ABCABCABC
 */
public class PrintABC {

    public static void main(String[] args) {
        Thread threadA = new Thread(new Printer("A", 1, 2));
        Thread threadB = new Thread(new Printer("B", 2, 3));
        Thread threadC = new Thread(new Printer("C", 3, 1));

        threadA.setDaemon(false);
        threadB.setDaemon(false);
        threadC.setDaemon(false);

        threadA.start();
        threadB.start();
        threadC.start();
    }

    static final class Printer implements Runnable {
        private static final Object lock = new Object();
        private static volatile int status = 1;

        String content;
        int currSeq;
        int nextSeq;
        int printCount = 10;

        public Printer(String context, int currSeq, int nextSeq) {
            this.content = context;
            this.currSeq = currSeq;
            this.nextSeq = nextSeq;
        }

        @Override
        public void run() {
            for (int i = 0; i < printCount; i++) {
                synchronized (lock) {
                    try {
                        while (status != currSeq) {
                            lock.wait();
                        }
                    } catch(InterruptedException ex) {
                        ex.printStackTrace();;
                    }

                    System.out.println(content);
                    status = nextSeq;
                    lock.notifyAll();
                }
            }
        }
    }







//===================V2=========================
//    public static void main(String[] args) {
//        Thread t1 = new Thread(new HPPrinter("A", 1, 2));
//        Thread t2 = new Thread(new HPPrinter("B", 2, 3));
//        Thread t3 = new Thread(new HPPrinter("C", 3, 1));
//
//        t1.setDaemon(false);
//        t2.setDaemon(false);
//        t3.setDaemon(false);
//
//        t1.start();
//        t2.start();
//        t3.start();
//    }
//
//    static final class HPPrinter implements Runnable {
//        private static volatile int status = 1;
//        private static final Object syncLock = new Object();
//        private int printCount = 100;
//        private int currSeq;
//        private int nextSeq;
//        String content;
//
//        public HPPrinter(String content, int currSeq,  int nextSeq) {
//            this.content = content;
//            this.currSeq = currSeq;
//            this.nextSeq = nextSeq;
//        }
//
//        @Override
//        public void run() {
//            for (int i = 0; i < printCount; i++) {
//                synchronized (syncLock) {
//                    while (status != currSeq) {
//                        try {
//                            syncLock.wait();
//                        } catch (InterruptedException ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//                    System.out.println(content);
//                    status = nextSeq;
//                    syncLock.notifyAll();
//                }
//            }
//        }
//    }


//    ===================V1=========================
//    public static void main(String[] args) {
//        HPPrinter printer = new HPPrinter();
//        new Thread(() -> {
//            printer.printA();
//        }).start();
//        new Thread(() -> {
//            printer.printB();
//        }).start();
//        new Thread(() -> {
//            printer.printC();
//        }).start();
//
//        try {
//            Thread.sleep(3600000);
//        } catch (Exception ex) {
//
//        }
//    }
//    static final class PrinterV1 {
//        private int status = 1;
//
//        public void printA() {
//            try {
//                for(;;) {
//                    System.out.println('A');
//                    notifyB();
//                    waitA();
//                }
//            } catch (InterruptedException ex) {
//
//            }
//        }
//
//        public void printB() {
//            try {
//                for(;;) {
//                    waitB();
//                    System.out.println('B');
//                    notifyC();
//                }
//            } catch (InterruptedException ex) {
//
//            }
//        }
//
//        public void printC() {
//            try {
//                for(;;) {
//                    waitC();
//                    System.out.println('C');
//                    notifyA();
//                }
//            } catch (InterruptedException ex) {
//
//            }
//        }
//
//        public synchronized void waitA() throws InterruptedException {
//            while (status != 1) {
//                wait();
//            }
//        }
//
//        public synchronized void waitB() throws InterruptedException {
//            while (status != 2) {
//                wait();
//            }
//        }
//
//        public synchronized void waitC() throws InterruptedException {
//            while (status != 3) {
//                wait();
//            }
//        }
//
//        public synchronized void notifyA() {
//            status = 1;
//            notifyAll();
//        }
//
//        public synchronized void notifyB() {
//            status = 2;
//            notifyAll();
//        }
//
//        public synchronized void notifyC() {
//            status = 3;
//            notifyAll();
//        }
//    }
}
