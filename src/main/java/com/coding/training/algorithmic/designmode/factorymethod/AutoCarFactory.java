package com.coding.training.algorithmic.designmode.factorymethod;

public class AutoCarFactory implements ICarFactory {
    @Override
    public ICarProduct create() {
        return new AutoCarProduct();
    }
}
