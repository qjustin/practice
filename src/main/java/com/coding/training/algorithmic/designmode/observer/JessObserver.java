package com.coding.training.algorithmic.designmode.observer;

public class JessObserver implements IObserver {
    @Override
    public void update(String message) {
        System.out.println("Jess received message:" + message);
    }
}
