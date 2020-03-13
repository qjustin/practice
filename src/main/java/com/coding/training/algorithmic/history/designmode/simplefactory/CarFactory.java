package com.coding.training.algorithmic.history.designmode.simplefactory;

public class CarFactory {
    public static ICarProduct create(String brand) {
        ICarProduct carProduct = null;

        if ("bmw".equals(brand)) {
            carProduct = new BMWCarProduct();
        } else if ("auto".equals(brand)) {
            carProduct = new AutoCarProduct();
        }

        return carProduct;
    }
}
