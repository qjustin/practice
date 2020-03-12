package com.coding.training.algorithmic.designmode.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class App {
    public static void main(String[] args) {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        IReal realObject = new Real();

        InvocationHandler handler = new Delegate(realObject);

        IReal proxyHello = (IReal) Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(), handler);

        System.out.println(proxyHello.getHashCode());
    }
}
