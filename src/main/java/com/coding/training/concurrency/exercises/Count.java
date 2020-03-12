package com.coding.training.concurrency.exercises;

public class Count {
    Lock lock = new Lock();

    // 线程可以进入任何一个它已经拥有的锁所同步着的代码块。
    // 第一个线程执行print()方法，得到了锁，使lockedBy等于当前线程，也就是说，执行的这个方法的线程获得了这个锁，执行add()方法时，
    // 同样要先获得锁，因不满足while循环的条件，也就是不等待，继续进行，将此时的lockedCount变量，也就是当前获得锁的数量加一，
    // 当释放了所有的锁，才执行notify()。如果在执行这个方法时，有第二个线程想要执行这个方法，因为lockedBy不等于第二个线程，
    // 导致这个线程进入了循环，也就是等待，不断执行wait()方法。只有当第一个线程释放了所有的锁，执行了notify()方法，
    // 第二个线程才得以跳出循环，继续执行。
    public void print() {
        try {
            lock.lock();
            doAdd();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void doAdd() {
        try {
            lock.lock(); //do something lock.unlock();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
