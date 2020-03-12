package com.coding.training.algorithmic.designmode.simplefactory;

public class RefectCarFactory {

    public static <T extends ICarProduct> T create(Class<T> klass) {
        ICarProduct product = null;
        try {
            product = (ICarProduct) Class.forName(klass.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
