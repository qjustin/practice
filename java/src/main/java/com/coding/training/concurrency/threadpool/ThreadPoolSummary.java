package com.coding.training.concurrency.threadpool;

import java.io.IOException;
import java.util.concurrent.*;


public class ThreadPoolSummary {
    public static final ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println("caught " + e);
                }
            });

            return t;
        }
    };

    public static void main(String[] args) throws IOException {

        /**
         *   ThreadPoolExecutor 线程池：
         *
         *   1. corePoolSize: 线程池的基本大小：当提交一个任务到线程池时，线程池会创建一个线程来执行任务，即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时就不再创建。
         *      如果调用了线程池的prestartAllCoreThreads()方法，线程池会提前创建并启动所有基本线程。
         *
         *   2. maximumPoolSize: 线程池最大数量：线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数， 则线程池会再创建新的线程执行任务。值得注意的是，如果使用了无界的任务队列这个参数就没什么效果。
         *
         *   3. keepAliveTime: 线程活动保持时间：线程池的工作线程空闲后，保持存活的时间。所以，如果任务很多，并且每个任务执行的时间比较短，可以调大时间，提高线程的利用率
         *
         *   4. TimeUnit: 线程活动保持时间的单位
         *
         *   5. ThreadFactory: 用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字。使用开源框架guava提供的ThreadFactoryBuilder可以快速给线程池里的线程设置有意义的名字
         *
         *   6. BlockingQueue<Runnable>: 任务队列：用于保存等待执行的任务的阻塞队列。可以选择以下几个阻塞队列。
         *       ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按FIFO（先进先出）原则对元素进行排序。
         *       LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列。
         *       SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于Linked-BlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
         *       PriorityBlockingQueue：一个具有优先级的无限阻塞队列。
         *
         *   7. RejectedExecutionHandler: 饱和策略：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。这个策略默认情况下是AbortPolicy，表示无法处理新任务时抛出异常。
         *       AbortPolicy：直接抛出异常。
         *       CallerRunsPolicy：只用调用者所在线程来运行任务。
         *       DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
         *       DiscardPolicy：不处理，丢弃掉。
         *
         *  构造函数：
         *
         *  ThreadPoolExecutor threadPool1 = new ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue);
         *  ThreadPoolExecutor threadPool2 = new ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory);
         *  ThreadPoolExecutor threadPool3 = new ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler);
         *  ThreadPoolExecutor threadPool4 = new ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)

         *   五种线程池的适应场景：
         *
         *   newCachedThreadPool：用来创建一个可以无限扩大的线程池，适用于服务器负载较轻，执行很多短期异步任务。
         *
         *   newFixedThreadPool：创建一个固定大小的线程池，因为采用无界的阻塞队列，所以实际线程数量永远不会变化，适用于可以预测线程数量的业务中，或者服务器负载较重，对当前线程数量进行限制。
         *
         *   newSingleThreadExecutor：创建一个单线程的线程池，适用于需要保证顺序执行各个任务，并且在任意时间点，不会有多个线程是活动的场景。
         *
         *   newScheduledThreadPool：可以延时启动，定时启动的线程池，适用于需要多个后台线程执行周期任务的场景。
         *
         *   newWorkStealingPool：创建一个拥有多个任务队列的线程池，可以减少连接数，创建当前可用cpu数量的线程来并行执行，适用于大耗时的操作，可以并行来执行
         */

        ExecutorService singleExecutor1 = Executors.newSingleThreadExecutor();
        // new FinalizableDelegatedExecutorService (new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
        ExecutorService singleExecutor2 = Executors.newSingleThreadExecutor(threadFactory);
        // new FinalizableDelegatedExecutorService (new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory));

        ExecutorService fixedPool1 = Executors.newFixedThreadPool(10);
        // new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        ExecutorService fixedPool2 = Executors.newFixedThreadPool(10, threadFactory);
        // new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);

        ExecutorService cachedPool1 = Executors.newCachedThreadPool();
        // new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>())
        ExecutorService cachedPool2 = Executors.newCachedThreadPool(threadFactory);
        // new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), threadFactory);

        ExecutorService workStealingPool1 = Executors.newWorkStealingPool();
        // new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true)
        ExecutorService workStealingPool2 = Executors.newWorkStealingPool(10);
        // new ForkJoinPool(10, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true)

        ScheduledExecutorService scheduledPool1 = Executors.newScheduledThreadPool(10);
        //  new ThreadPoolExecutor(10, Integer.MAX_VALUE, 0, NANOSECONDS, new ScheduledThreadPoolExecutor.DelayedWorkQueue())
        ScheduledExecutorService scheduledPool2 = Executors.newScheduledThreadPool(10, threadFactory);
        //  new ThreadPoolExecutor(10, Integer.MAX_VALUE, 0, NANOSECONDS, new ScheduledThreadPoolExecutor.DelayedWorkQueue(), threadFactory)

        ScheduledExecutorService singleScheduledExecutor1 = Executors.newSingleThreadScheduledExecutor();
        // new Executors.DelegatedScheduledExecutorService (new ScheduledThreadPoolExecutor(new ThreadPoolExecutor(1, Integer.MAX_VALUE, 0, NANOSECONDS, new ScheduledThreadPoolExecutor.DelayedWorkQueue()));
        ScheduledExecutorService singleScheduledExecutor2 = Executors.newSingleThreadScheduledExecutor(threadFactory);
        // new Executors.DelegatedScheduledExecutorService (new ScheduledThreadPoolExecutor(new ThreadPoolExecutor(1, Integer.MAX_VALUE, 0, NANOSECONDS, new ScheduledThreadPoolExecutor.DelayedWorkQueue(), threadFactory));


        ScheduledFuture<?> future = scheduledPool1.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("60 seconds latter");
            }
        }, 60, TimeUnit.SECONDS);

        scheduledPool1.shutdown();


    }
}
