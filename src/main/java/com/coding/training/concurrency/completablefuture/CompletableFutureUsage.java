package com.coding.training.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * https://www.ibm.com/developerworks/cn/java/j-cf-of-jdk8/index.html
 *
 * CompletableFuture 类介绍
 * Java 8 中, 新增加了一个包含 50 个方法左右的类--CompletableFuture，它提供了非常强大的 Future 的扩展功能，
 * 可以帮助我们简化异步编程的复杂性，并且提供了函数式编程的能力，可以通过回调的方式处理计算结果，也提供了转换和组合
 * CompletableFuture 的方法。
 *
 * 对于阻塞或者轮询方式，依然可以通过 CompletableFuture 类的 CompletionStage 和 Future 接口方式支持。
 *
 * CompletableFuture 类声明了 CompletionStage 接口，CompletionStage 接口实际上提供了同步或异步运行
 * 计算的舞台，所以我们可以通过实现多个 CompletionStage 命令，并且将这些命令串联在一起的方式实现多个命令
 * 之间的触发。
 *
 * 我们可以通过 CompletableFuture.supplyAsync(this::sendMsg); 这么一行代码创建一个简单的异步计算。在这行代码中，
 * supplyAsync 支持异步地执行我们指定的方法，这个例子中的异步执行方法是 sendMsg。当然，我们也可以使用 Executor 执行
 * 异步程序，默认是 ForkJoinPool.commonPool()。
 *
 * 我们也可以在异步计算结束之后指定回调函数，例如 CompletableFuture.supplyAsync(this::sendMsg) .thenAccept(this::notify);
 * 这行代码中的 thenAccept 被用于增加回调函数，在我们的示例中 notify 就成了异步计算的消费者，它会处理计算结果。
 *
 */
public class CompletableFutureUsage {
    // 创建完整的 CompletableFuture
    static void completedFutureExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
        System.out.println(cf.isDone());

        //getNow(null)返回计算结果或者 null
        System.out.println(cf.getNow(null));
    }


    // 使用固定的线程池完成异步执行动作示例
    static void runAsyncExample() {
        //CompletableFuture 是异步执行方式；
        // 使用 ForkJoinPool 实现异步执行，这种方式使用了 daemon 线程执行 Runnable 任务。

        CompletableFuture<Void>cf = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().isDaemon());
            randomSleep();
        });
        System.out.println(cf.isDone());
        sleepEnough();
        System.out.println(cf.isDone());
    }



    static void randomSleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void sleepEnough() {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
