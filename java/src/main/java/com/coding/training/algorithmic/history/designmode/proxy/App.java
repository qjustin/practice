package com.coding.training.algorithmic.history.designmode.proxy;

/**
 * java.lang.reflect.Proxy
 */
public class App {
    public static void main(String[] args) {
        IAction action = new Proxy(new Real());
        action.doWork();
    }
}
