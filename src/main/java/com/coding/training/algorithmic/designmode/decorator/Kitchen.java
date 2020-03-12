package com.coding.training.algorithmic.designmode.decorator;

public class Kitchen extends AbstractRoomDecorator {
    public Kitchen(IRoom room) {
        super(room);
    }

    @Override
    public void fitment() {
        super.fitment();
        addKitchenware();
    }

    private void addKitchenware() {
        System.out.println("装修成厨房：添加厨具");
    }
}
