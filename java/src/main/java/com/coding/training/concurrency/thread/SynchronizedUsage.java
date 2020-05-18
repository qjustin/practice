package com.coding.training.concurrency.thread;

/**
 * 方法内部的局部变量并不是临界资源，因为方法是在栈上执行的，而Java栈是线程私有的，因此不会产生线程安全问题。
 * <p>
 * Java中，提供了两种方式来实现同步互斥访问：synchronized和Lock
 * <p>
 * 对于synchronized方法或者synchronized代码块，当出现异常时，JVM会自动释放当前线程占用的锁，因此不会由于异常导致出现死锁现象
 * <p>
 * 普通同步方法，锁是当前实例对象。仅仅为当前一个实例加锁, 如果new多个对象，则每个线程都持有自己的锁。
 * 静态同步方法，锁是当前类的class对象。 当前类型的所有实例都将被加锁 等价与synchronized(xxx.class) { }
 * 同步代码块，锁是括号中的对象。锁定范围，需要根据括号中的对象共享范围来确定
 */
public class SynchronizedUsage {
    public static void main(String[] args) {
        SyncBlock.doCorrect();
    }
}

/**
 * 1. 普通同步方法，锁是当前实例对象。
 * doCorrect 方法 所有线程共享同一个对象。测试结果: 900000
 * doInCorrect 方法 每个线程创建自己的对象。测试结果: 小于900000
 */
class SyncMethod {
    public static long flag = 0L;

    private synchronized long increament() {
        flag = flag + 1;
        return flag;
    }

    public static void doCorrect() {
        SyncMethod syncMethod = new SyncMethod();

        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(syncMethod.increament());
        }, "thread-01").start();

        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(syncMethod.increament());
        }, "thread-03").start();

        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(syncMethod.increament());
        }, "thread-04").start();
    }

    public static void doIncorrect() {
        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(new SyncMethod().increament());
        }, "thread-01").start();

        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(new SyncMethod().increament());
        }, "thread-03").start();

        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(new SyncMethod().increament());
        }, "thread-04").start();
    }
}

/**
 * 2. 静态同步方法，锁是当前类的class对象。该类型的所有实例都将加锁, 锁范围很大
 * 注意每个线程new自己的SyncStaticMethod结果仍然正确
 *
 * case1 与 case2 效果相同 都是锁是当前类的class对象,也就是说类型的所有实例都将加锁
 */
class SyncStaticMethod {
    public static long flag = 0L;

    // test case1
    private synchronized static long increament() {
        flag = flag + 1;
        return flag;
    }

    // test case2
    private long innerIncreament() {
        synchronized (SyncStaticMethod.class) {
            // 不论多少个实例，某个一时刻只允许有一个线程进入到这里
            flag = flag + 1;
        }

        return flag;
    }

//    public static void doCorrect() {
//        new Thread(() -> {
//            for (int i = 0; i < 300000; i++)
//                System.out.println(new SyncStaticMethod().increament());
//        }, "thread-01").start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 300000; i++)
//                System.out.println(new SyncStaticMethod().increament());
//        }, "thread-03").start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 300000; i++)
//                System.out.println(new SyncStaticMethod().increament());
//        }, "thread-04").start();
//    }

    public static void doCorrect() {
        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(new SyncStaticMethod().innerIncreament());
        }, "thread-01").start();

        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(new SyncStaticMethod().innerIncreament());
        }, "thread-03").start();

        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(new SyncStaticMethod().innerIncreament());
        }, "thread-04").start();
    }
}

class SyncBlock {
    public static long flag = 0L;

    public static final Object obj = new Object();

    private long increament() {
        synchronized (obj) {
            flag = flag + 1;
        }

        return flag;
    }

//    private long increament() {
//        synchronized (this) {
//            flag = flag + 1;
//        }
//
//        return flag;
//    }

    public static void doCorrect() {
        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(new SyncBlock().increament());
        }, "thread-01").start();

        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(new SyncBlock().increament());
        }, "thread-03").start();

        new Thread(() -> {
            for (int i = 0; i < 300000; i++)
                System.out.println(new SyncBlock().increament());
        }, "thread-04").start();
    }
}



