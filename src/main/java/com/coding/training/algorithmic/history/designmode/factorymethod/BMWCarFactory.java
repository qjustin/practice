package com.coding.training.algorithmic.history.designmode.factorymethod;

public class BMWCarFactory implements ICarFactory {
    @Override
    public ICarProduct create() {
        return new BMWCarProduct();
    }
}
