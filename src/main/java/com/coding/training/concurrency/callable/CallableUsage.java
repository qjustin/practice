package com.coding.training.concurrency.callable;


import java.util.concurrent.*;

/**
 * Callable、Future和FutureTask 使用方法
 * <p>
 * Callable:
 * <p>
 * Callable的 call() 方法可以返回值和抛出异常，而Runnable的run()方法没有这些功能。Callable可以返回装载有计算结果的Future对象。
 * Callable一般情况下是配合ExecutorService来使用的，在ExecutorService接口中声明了若干个submit方法的重载版
 * <p>
 * <p>
 * Future:
 * 在Future接口中声明了5个方法，下面依次解释每个方法的作用：
 * <p>
 * cancel方法用来取消任务，如果取消任务成功则返回true，如果取消任务失败则返回false。
 *          参数mayInterruptIfRunning表示是否允许取消正在执行却没有执行完毕的任务，
 *          如果设置true，则表示可以取消正在执行过程中的任务。如果任务已经完成，则无论
 *          mayInterruptIfRunning为true还是false，此方法肯定返回false，即如果取
 *          消已经完成的任务会返回false；如果任务正在执行，若mayInterruptIfRunning
 *          设置为true，则返回true，若mayInterruptIfRunning设置为false，则返回false；
 *          如果任务还没有执行，则无论mayInterruptIfRunning为true还是false，肯定返回true。
 *
 * isCancelled方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true。
 * isDone方法表示任务是否已经完成，若任务完成，则返回true；
 * get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；
 * get(long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null。
 * 　　也就是说Future提供了三种功能：
 * <p>
 * 　　1）判断任务是否完成；
 * <p>
 * 　　2）能够中断任务；
 * <p>
 * 　　3）能够获取任务执行结果。
 * Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果。必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
 * 因为Future只是一个接口，所以是无法直接用来创建对象使用的，因此就有了下面的FutureTask。
 * <p>
 * FutureTask
 * FutureTask是Future接口的一个唯一实现类
 * 所以它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值。
 */
public class CallableUsage {


    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();
        CallableImpl task = new CallableImpl();

////         Begin
////         1.使用Callable+Future获取执行结果
//        Future<Integer> result = executor.submit(task);
//
////         2. 使用Callable+FutureTask获取执行结果
////         2.1 第一种方式
//         FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
//         executor.submit(futureTask);

////         2.2 第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();

//            3. 如果只是为了取消性而使用 Future 但又不提供可用的结果，则可以声明 Future<?> 形式类型、并返回 null 作为底层任务的结果。
////         End


        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            // 1.使用Callable+Future获取执行结果
//            System.out.println("task运行结果" + result.get());

            // 2. 使用Callable+FutureTask获取执行结果
            System.out.println("task运行结果" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}