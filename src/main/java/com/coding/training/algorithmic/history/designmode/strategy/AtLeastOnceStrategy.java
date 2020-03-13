package com.coding.training.algorithmic.history.designmode.strategy;

public class AtLeastOnceStrategy implements IStrategy {
    @Override
    public void commit() {
        System.out.println("消费消息");
        System.out.println("提交Offset");
    }
}
