package com.coding.training.algorithmic.designmode.singleton;

/**
 * 静态内部类懒汉模式
 *
 * JDK 中的 JDK 中 Runtime.getRuntime() 返回单例
 * 意图:一个类只有一个实例并提供全局访问点
 */
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {
    }

    public static StaticInnerClassSingleton getInstance() {
        return InstanceHolder.instance;
    }

    static class InstanceHolder {
        private static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }
}
