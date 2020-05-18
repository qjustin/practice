package com.coding.training.algorithmic.history.designmode.observer;

import java.util.ArrayList;
import java.util.List;

public class Postman implements IObservable {
    private List<IObserver> observers = new ArrayList<>();//保存收件人（观察者）的信息

    @Override
    public void register(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }
}
