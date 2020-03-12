package com.coding.training.algorithmic.designmode.template;

public class BMWCar extends AbstractCar {
    @Override
    protected void start() {
        System.out.println("start");
    }

    @Override
    protected void stop() {
        System.out.println("stop");
    }

    @Override
    protected void alarm() {
        System.out.println("didi");
    }

    @Override
    protected void engineBoom() {
        System.out.println("boom");
    }
}
