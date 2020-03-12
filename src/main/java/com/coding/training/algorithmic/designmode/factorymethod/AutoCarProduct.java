package com.coding.training.algorithmic.designmode.factorymethod;

public class AutoCarProduct implements ICarProduct{
    @Override
    public void name() {
        System.out.println("Auto");
    }
}
