package com.coding.training.algorithmic.history.designmode.proxy;

public class Proxy implements IAction {
    private IAction realObject;
    public Proxy(IAction realObject) {
        this.realObject = realObject;
    }

    @Override
    public void doWork() {
        realObject.doWork();
    }
}

