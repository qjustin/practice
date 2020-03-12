package com.coding.training.algorithmic.designmode.decorator;

public class Bedroom extends AbstractRoomDecorator {
    public Bedroom(IRoom room) {
        super(room);
    }

    @Override
    public void fitment() {
        super.fitment();
        addBedding();
    }

    private void addBedding() {
        System.out.println("装修成卧室：添加卧具");
    }
}
