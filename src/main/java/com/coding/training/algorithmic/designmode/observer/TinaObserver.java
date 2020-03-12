package com.coding.training.algorithmic.designmode.observer;

public class TinaObserver implements IObserver {
    @Override
    public void update(String message) {
        System.out.println("Tina received message:" + message);
    }
}
