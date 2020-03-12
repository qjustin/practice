package com.coding.training.algorithmic.designmode.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Delegate implements InvocationHandler {
    private IReal realObject;

    public Delegate(IReal realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("preInvoke");
        Object obj = method.invoke(realObject, args);
        System.out.println("postInvoke");
        return obj;
    }
}
