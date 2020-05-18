package com.coding.training.algorithmic.history.designmode.command;

public class MoveRightCommand implements ICommand {
    private ManReceiver man;
    private int x;

    public MoveRightCommand(ManReceiver man, int x) {
        this.man = man;
        this.x = x;
    }

    @Override
    public void execute() {
        man.moveRight(x);
    }

    @Override
    public void undo() {
        man.moveLeft(x);
    }
}
