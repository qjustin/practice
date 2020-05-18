package com.coding.training.algorithmic.history.designmode.singleton;

/**
 * “看起来”非常完美：既减少了阻塞，又避免了竞态条件。不错，但实际上仍然存在一个问题——当instance不为null时，仍可能指向一个"被部分初始化的对象"。
 *
 * 问题出在这行简单的赋值语句：
 *
 * instance = new Singleton();
 * 它并不是一个原子操作。事实上，它可以”抽象“为下面几条JVM指令：
 *
 * memory = allocate();    //1：分配对象的内存空间
 * initInstance(memory);   //2：初始化对象
 * instance = memory;      //3：设置instance指向刚分配的内存地址
 * 上面操作2依赖于操作1，但是操作3并不依赖于操作2，所以JVM可以以“优化”为目的对它们进行重排序，经过重排序后如下：
 *
 * memory = allocate();    //1：分配对象的内存空间
 * instance = memory;      //3：设置instance指向刚分配的内存地址（此时对象还未初始化）
 * ctorInstance(memory);   //2：初始化对象
 *
 * 可以看到指令重排之后，操作 3 排在了操作 2 之前，即引用instance指向内存memory时，这段崭新的内存还没有初始化——即，引用instance
 * 指向了一个"被部分初始化的对象"。此时，如果另一个线程调用getInstance方法，由于instance已经指向了一块内存空间，从而if条件判为false，
 * 方法返回instance引用，用户得到了没有完成初始化的“半个”单例。
 *
 * 解决这个该问题，只需要将instance声明为volatile变量：
 *
 * private static volatile Singleton instance;
 * 也就是说，在只有DCL没有volatile的懒加载单例模式中，仍然存在着并发陷阱。我确实不会拿到两个不同的单例了，但我会拿到“半个”单例（未完成初始化）。
 * 然而，许多面试书籍中，涉及懒加载的单例模式最多深入到DCL，却只字不提volatile。这“看似聪明”的机制，曾经被我广大初入Java世界的猿胞大加
 * 吹捧——我在大四实习面试跟谁学的时候，也得意洋洋的从饱汉、饿汉讲到Double Check，现在看来真是傻逼。对于考查并发的面试官而言，单例模式的实现
 * 就是一个很好的切入点，看似考查设计模式，其实期望你从设计模式答到并发和内存模型。
 *
 * volatile如何防止指令重排
 * volatile关键字通过“内存屏障”来防止指令被重排序。
 *
 * 为了实现volatile的内存语义，编译器在生成字节码时，会在指令序列中插入内存屏障来禁止特定类型的处理器重排序。然而，对于编译器来说，
 * 发现一个最优布置来最小化插入屏障的总数几乎不可能，为此，Java内存模型采取保守策略。
 *
 * 下面是基于保守策略的JMM内存屏障插入策略：
 *
 * 在每个volatile写操作的前面插入一个StoreStore屏障。
 * 在每个volatile写操作的后面插入一个StoreLoad屏障。
 * 在每个volatile读操作的后面插入一个LoadLoad屏障。
 * 在每个volatile读操作的后面插入一个LoadStore屏障。
 * 进阶
 * 在一次回答上述问题时，忘记了解释一个很容易引起疑惑的问题：
 *
 * 如果存在这种重排序问题，那么synchronized代码块内部不是也可能出现相同的问题吗？
 *
 * 即这种情况：
 *
 * class Singleton {
 *     ...
 *         if ( instance == null ) { //可能发生不期望的指令重排
 *             synchronized (Singleton.class) {
 *                 if ( instance == null ) {
 *                     instance = new Singleton();
 *                     System.out.println(instance.toString()); //程序顺序规则发挥效力的地方
 *                 }
 *             }
 *         }
 *     ...
 * }
 * 难道调用instance.toString()方法时，instance也可能未完成初始化吗？
 *
 * 首先还请放宽心，synchronized代码块内部虽然会重排序，但不会在代码块的范围内导致线程安全问题。
 *
 * Happens-Before内存模型和程序顺序规则
 * 程序顺序规则：如果程序中操作A在操作B之前，那么线程中操作A将在操作B之前执行。
 *
 * 前面说过，只有在Happens-Before内存模型中才会出现这样的指令重排序问题。Happens-Before内存模型维护了几种Happens-Before规则，
 * 程序顺序规则最基本的规则。程序顺序规则的目标对象是一段程序代码中的两个操作A、B，其保证此处的指令重排不会破坏操作A、B在代码中的先后顺序，
 * 但与不同代码甚至不同线程中的顺序无关。
 *
 * 因此，在synchronized代码块内部，instance = new Singleton()仍然会指令重排序，但重排序之后的所有指令，仍然能够保证在
 * instance.toString()之前执行。进一步的，单线程中，if ( instance == null )能保证在synchronized代码块之前执行；但多线程中，
 * 线程1中的if ( instance == null )却与线程2中的synchronized代码块之间没有偏序关系，因此线程2中synchronized代码块内部的指令
 * 重排对于线程1是不期望的，导致了此处的并发陷阱。
 *
 * 类似的Happens-Before规则还有volatile变量规则、监视器锁规则等。程序猿可以借助（Piggyback）现有的Happens-Before规则来保持内存
 * 可见性和防止指令重排。
 *
 * JDK 中的 JDK 中 Runtime.getRuntime() 返回单例
 * 意图:一个类只有一个实例并提供全局访问点
 */
public final class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton instance;

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }

        return instance;
    }





































    // 不加volatile 当instance不为null时，仍可能指向一个"被部分初始化的对象"。
//    private static volatile DoubleCheckSingleton instance;
//
//    private DoubleCheckSingleton() {
//    }
//
//    public static DoubleCheckSingleton getInstance() {
//        if (instance == null) {
//            synchronized (DoubleCheckSingleton.class) {
//                if (instance == null) {
//                    instance = new DoubleCheckSingleton();
//                }
//            }
//        }
//
//        return instance;
//    }
}
