package com.coding.training.algorithmic.designmode.factorymethod;

/**
 * Factory:
 * 简单来说，按照需求返回一个类型的实例。
 *
 * java.lang.Proxy#newProxyInstance()
 * java.lang.Object#toString()
 * java.lang.Class#newInstance()
 * java.lang.reflect.Array#newInstance()
 * java.lang.reflect.Constructor#newInstance()
 * java.lang.Boolean#valueOf(String)
 * java.lang.Class#forName()
 */
public class App {
    public static void main(String[] args) {
        ICarFactory bmwFactory = new BMWCarFactory();
        ICarFactory autoFactory = new AutoCarFactory();

        ICarProduct bmwProduct = bmwFactory.create();
        ICarProduct autoProduct = autoFactory.create();

        bmwProduct.name();
        autoProduct.name();
    }
}
