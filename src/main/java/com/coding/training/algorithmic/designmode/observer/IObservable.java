package com.coding.training.algorithmic.designmode.observer;

public interface IObservable {//抽象被观察者
    void register(IObserver observer);//添加观察者

    void unregister(IObserver observer);//删除观察者

    void notify(String message);//通知观察者
}
