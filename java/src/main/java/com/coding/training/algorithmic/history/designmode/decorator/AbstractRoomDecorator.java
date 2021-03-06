package com.coding.training.algorithmic.history.designmode.decorator;

public abstract class AbstractRoomDecorator implements IRoom {
    private IRoom room;

    public AbstractRoomDecorator(IRoom room) {
        this.room = room;
    }

    @Override
    public void fitment() {
        room.fitment();
    }
}
