package com.coding.training.concurrency.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * http://www.cnblogs.com/dolphin0520/p/3923167.html
 *
 * synchronized线程释放锁只会有两种情况：
 * 　　1）获取锁的线程执行完了该代码块，然后线程释放对锁的占有；
 * 　　2）线程执行发生异常，此时JVM会让线程自动释放锁。
 * <p>
 * <p>
 * synchronized关键字来实现同步的缺点：
 * 1. 多个线程都只是进行读操作，无法实现
 * 2. synchronized修饰的话，当一个线程处于等待某个锁的状态，是无法被中断的，只有一直等待下去。
 * <p>
 * Lock和synchronized有一点非常大的不同，采用synchronized不需要用户去手动释放锁
 *
 * <p>
 * public interface Lock {
 * void lock();
 * void lockInterruptibly() throws InterruptedException;
 * boolean tryLock();
 * boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
 * void unlock();
 * Condition newCondition();
 * }
 * ----------------------------------------------------------------------------------------------------------------------------------
 * // 获取锁。如果锁已被其他线程获取，则进行等待
 * lock.lock();
 * try{
 * //处理任务
 * }catch(Exception ex){
 * <p>
 * }finally{
 * lock.unlock();   //释放锁
 * }
 * ----------------------------------------------------------------------------------------------------------------------------------
 * // tryLock()尝试获取锁，如果获取成功，则返回true，如果获取失败，则返回false，也就说这个方法无论如何都会立即返回。在拿不到锁时不会一直在那等待
 * if(lock.tryLock()) {
 * try{
 * //处理任务
 * }catch(Exception ex){
 * <p>
 * }finally{
 * lock.unlock();   //释放锁
 * }
 * }else {
 * //如果不能获取锁，则直接做其他事情
 * }
 * ----------------------------------------------------------------------------------------------------------------------------------
 * // 当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，而线程B只有在等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程
 * public void method() throws InterruptedException {
 * lock.lockInterruptibly();
 * try {
 * //.....
 * }
 * finally {
 * lock.unlock();
 * }
 * }
 * ----------------------------------------------------------------------------------------------------------------------------------
 * ReentrantLock是唯一实现了Lock接口的类，并且ReentrantLock提供了更多的方法。
 *
 * Lock和synchronized的选择
 *
 * 　　总结来说，Lock和synchronized有以下几点不同：
 *
 * 　　1）Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
 *
 * 　　2）synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
 *
 * 　　3）Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
 *
 * 　　4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
 *
 * 　　5）Lock可以提高多个线程进行读操作的效率。
 *
 * 　　在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，在具体使用时要根据适当情况选择。
 * ----------------------------------------------------------------------------------------------------------------------------------
 */
public class LockUsage {
    public static void main(String[] args) {
//        LockUsageDemo.doTest();
//        TryLockDemo.doTest();
//        LockInterruptiblyDemo.doTest();
        ReentrantReadWriteLockDemo.doTest();
    }
}

class LockUsageDemo {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public static void doTest() {
        final LockUsageDemo demo = new LockUsageDemo();

        new Thread() {
            public void run() {
                demo.insert(Thread.currentThread());
            }

            ;
        }.start();

        new Thread() {
            public void run() {
                demo.insert(Thread.currentThread());
            }

            ;
        }.start();
    }

    public void insert(Thread thread) {
        // 在insert方法中的lock变量是局部变量，每个线程执行该方法时都会保存一个副本，
        // 那么理所当然每个线程执行到lock.lock()处获取的是不同的锁，所以就不会发生冲突。
        Lock lock = new ReentrantLock();    //注意这个地方
        lock.lock();
        try {
            System.out.println(thread.getName() + "得到了锁");
            for (int i = 0; i < 5; i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println(thread.getName() + "释放了锁");
            lock.unlock();
        }
    }
}

class TryLockDemo {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //注意这个地方

    public static void doTest() {
        final TryLockDemo demo = new TryLockDemo();

        new Thread(() ->  {
            demo.insert(Thread.currentThread());
        }).start();

        new Thread(() ->  {
            demo.insert(Thread.currentThread());
        }).start();
    }

    public void insert(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println(thread.getName() + "得到了锁");
                for (int i = 0; i < 5; i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName() + "获取锁失败");
        }
    }
}

class LockInterruptiblyDemo {
    private Lock lock = new ReentrantLock();

    public static void doTest() {
        LockInterruptiblyDemo test = new LockInterruptiblyDemo();
        MyThread thread1 = new MyThread(test);
        MyThread thread2 = new MyThread(test);
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName() + "得到了锁");
            long startTime = System.currentTimeMillis();
            for (; ; ) {
                if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "执行finally");
            lock.unlock();
            System.out.println(thread.getName() + "释放了锁");
        }
    }
}

class MyThread extends Thread {
    private LockInterruptiblyDemo test = null;

    public MyThread(LockInterruptiblyDemo test) {
        this.test = test;
    }

    @Override
    public void run() {
        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断");
        }
    }
}

/**
 * 一个用来获取读锁，一个用来获取写锁。也就是说将文件的读写操作分开，分成2个锁来分配给线程，
 * 从而使得多个线程可以同时进行读操作。下面的ReentrantReadWriteLock实现了ReadWriteLock接口。
 * ReentrantReadWriteLock里面提供了很多丰富的方法，不过最主要的有两个方法：readLock()和writeLock()用来获取读锁和写锁。
 */
class ReentrantReadWriteLockDemo {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void doTest() {
        final ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();

        new Thread(() -> {
            demo.get(Thread.currentThread());
        }).start();

        new Thread(() -> {
            demo.get(Thread.currentThread());
        }).start();


        new Thread(() -> {
            demo.get(Thread.currentThread());
        }).start();

    }

    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + "正在进行读操作");
            }

            System.out.println(thread.getName() + "读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }
}

/**
 * 在前面介绍了Lock的基本使用，这一节来介绍一下与锁相关的几个概念。
 *
 * 　　1.可重入锁
 *
 * 　　如果锁具备可重入性，则称作为可重入锁。像synchronized和ReentrantLock都是可重入锁，可重入性在我看来实际上表明了锁的分配机制：基于线程的分配，而不是基于方法调用的分配。举个简单的例子，当一个线程执行到某个synchronized方法时，比如说method1，而在method1中会调用另外一个synchronized方法method2，此时线程不必重新去申请锁，而是可以直接执行方法method2。
 *
 * 　　看下面这段代码就明白了：

 * class MyClass {
 *     public synchronized void method1() {
 *         method2();
 *     }
 *
 *     public synchronized void method2() {
 *
 *     }
 * }
 *  　　上述代码中的两个方法method1和method2都用synchronized修饰了，假如某一时刻，线程A执行到了method1，此时线程A获取了这个对象的锁，而由于method2也是synchronized方法，假如synchronized不具备可重入性，此时线程A需要重新申请锁。但是这就会造成一个问题，因为线程A已经持有了该对象的锁，而又在申请获取该对象的锁，这样就会线程A一直等待永远不会获取到的锁。
 *
 * 　　而由于synchronized和Lock都具备可重入性，所以不会发生上述现象。
 *
 * 　　2.可中断锁
 *
 * 　　可中断锁：顾名思义，就是可以相应中断的锁。
 *
 * 　　在Java中，synchronized就不是可中断锁，而Lock是可中断锁。
 *
 * 　　如果某一线程A正在执行锁中的代码，另一线程B正在等待获取该锁，可能由于等待时间过长，线程B不想等待了，想先处理其他事情，我们可以让它中断自己或者在别的线程中中断它，这种就是可中断锁。
 *
 * 　　在前面演示lockInterruptibly()的用法时已经体现了Lock的可中断性。
 *
 * 　　3.公平锁
 *
 * 　　公平锁即尽量以请求锁的顺序来获取锁。比如同是有多个线程在等待一个锁，当这个锁被释放时，等待时间最久的线程（最先请求的线程）会获得该所，这种就是公平锁。
 *
 * 　　非公平锁即无法保证锁的获取是按照请求锁的顺序进行的。这样就可能导致某个或者一些线程永远获取不到锁。
 *
 * 　　在Java中，synchronized就是非公平锁，它无法保证等待的线程获取锁的顺序。
 *
 * 　　而对于ReentrantLock和ReentrantReadWriteLock，它默认情况下是非公平锁，但是可以设置为公平锁。
 *
 * 　　看一下这2个类的源代码就清楚了：
 *
 *
 *
 * 　　在ReentrantLock中定义了2个静态内部类，一个是NotFairSync，一个是FairSync，分别用来实现非公平锁和公平锁。
 *
 * 　　我们可以在创建ReentrantLock对象时，通过以下方式来设置锁的公平性：
 * ReentrantLock lock = new ReentrantLock(true);
 *  　　如果参数为true表示为公平锁，为fasle为非公平锁。默认情况下，如果使用无参构造器，则是非公平锁。
 *
 *
 *
 * 　　另外在ReentrantLock类中定义了很多方法，比如：
 *
 * 　　isFair()        //判断锁是否是公平锁
 *
 * 　　isLocked()    //判断锁是否被任何线程获取了
 *
 * 　　isHeldByCurrentThread()   //判断锁是否被当前线程获取了
 *
 * 　　hasQueuedThreads()   //判断是否有线程在等待该锁
 *
 * 　　在ReentrantReadWriteLock中也有类似的方法，同样也可以设置为公平锁和非公平锁。不过要记住，ReentrantReadWriteLock并未实现Lock接口，它实现的是ReadWriteLock接口。
 *
 * 　　4.读写锁
 *
 * 　　读写锁将对一个资源（比如文件）的访问分成了2个锁，一个读锁和一个写锁。
 *
 * 　　正因为有了读写锁，才使得多个线程之间的读操作不会发生冲突。
 *
 * 　　ReadWriteLock就是读写锁，它是一个接口，ReentrantReadWriteLock实现了这个接口。
 *
 * 　　可以通过readLock()获取读锁，通过writeLock()获取写锁。
 *
 * 　　上面已经演示过了读写锁的使用方法，在此不再赘述。
 *
 *
 */
