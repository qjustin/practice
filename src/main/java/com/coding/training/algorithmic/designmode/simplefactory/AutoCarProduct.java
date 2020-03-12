package com.coding.training.algorithmic.designmode.simplefactory;


public class AutoCarProduct implements ICarProduct{
    @Override
    public void name() {
        System.out.println("Auto");
    }
}
