package com.coding.training.algorithmic.history.designmode.command;

public class MoveLeftCommand implements ICommand {
    private ManReceiver man;
    private int x;

    public MoveLeftCommand(ManReceiver man, int x) {
        this.man = man;
        this.x = x;
    }

    @Override
    public void execute() {
        man.moveLeft(x);
    }

    @Override
    public void undo() {
        man.moveRight(x);
    }
}
