package com.coding.training.algorithmic.history.designmode.factorymethod;

public class AutoCarFactory implements ICarFactory {
    @Override
    public ICarProduct create() {
        return new AutoCarProduct();
    }
}
