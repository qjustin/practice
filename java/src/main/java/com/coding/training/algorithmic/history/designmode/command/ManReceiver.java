package com.coding.training.algorithmic.history.designmode.command;

/**
 * 接受者角色，它负责请求的具体实施
 */
public class ManReceiver {
    private int x = 0;

    public void moveLeft(int x) {
        this.x -= x;
        System.out.println(this.x);
    }

    public void moveRight(int x) {
        this.x += x;
        System.out.println(this.x);
    }
}
