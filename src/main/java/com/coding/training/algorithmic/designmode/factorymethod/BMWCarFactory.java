package com.coding.training.algorithmic.designmode.factorymethod;

public class BMWCarFactory implements ICarFactory {
    @Override
    public ICarProduct create() {
        return new BMWCarProduct();
    }
}
