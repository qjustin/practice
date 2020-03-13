package com.coding.training.algorithmic.history.designmode.proxy;

public class Real implements IAction {
    @Override
    public void doWork() {
        System.out.println("doWork");
    }
}
