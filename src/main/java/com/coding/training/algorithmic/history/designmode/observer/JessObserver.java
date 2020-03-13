package com.coding.training.algorithmic.history.designmode.observer;

public class JessObserver implements IObserver {
    @Override
    public void update(String message) {
        System.out.println("Jess received message:" + message);
    }
}
