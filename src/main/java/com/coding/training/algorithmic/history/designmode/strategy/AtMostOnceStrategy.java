package com.coding.training.algorithmic.history.designmode.strategy;

public class AtMostOnceStrategy implements IStrategy {
    @Override
    public void commit() {
        System.out.println("提交Offset");
        System.out.println("消费消息");
    }
}
