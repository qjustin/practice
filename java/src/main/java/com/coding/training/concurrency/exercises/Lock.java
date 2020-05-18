package com.coding.training.concurrency.exercises;
//
//// 不可重入锁 指的是每次使用锁方法时，都需要重新获取锁，即使在同一线程中调用同一方法都需要等待上一个锁的释放。它是基于method粒度的，per-invocation。
//public class Lock {
//    private boolean isLocked = false;
//
//    public synchronized void lock() throws InterruptedException {
//        while (isLocked) {
//            wait();
//        }
//        isLocked = true;
//    }
//
//    public synchronized void unlock() {
//        isLocked = false;
//        notify();
//    }
//}
//
//public class Count {
//    Lock lock = new Lock();
//
//    // 当调用print()方法时，获得了锁，这时就无法再调用doAdd()方法，这时必须先释放锁才能调用，
//    public void print() {
//        lock.lock();
//        doAdd();
//        lock.unlock();
//    }
//
//    public void doAdd() {
//        lock.lock();
//    }
//}
//

// 可重入锁 指的是在同一个thread中，获取锁之后再次使用同样的方法或对象中的其他方法可以直接操作，而不需要重新获取锁。它是基于thread粒度的，per-thread。
public class Lock {
    boolean isLocked = false;
    Thread lockedBy = null;
    int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLocked && lockedBy != thread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}
