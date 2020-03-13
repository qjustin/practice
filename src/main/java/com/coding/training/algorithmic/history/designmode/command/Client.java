package com.coding.training.algorithmic.history.designmode.command;

/**
 * 将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，对请求排队或者记录日志，可以提供命令的撤销和恢复功能。
 *
 * 命令模式属于行为型模式。
 * 我们遇到最常见的命令模式就是关机操作了，我们只需点击一下关机按钮就可以了，至于计算机是如何关机的，我们不需要关心其实现细节。
 *
 * 角色说明：
 * Command（命令角色）：接口或者抽象类，定义要执行的命令。
 * ConcreteCommand（具体命令角色）：命令角色的具体实现，通常会持有接收者，并调用接收者来处理命令。
 * Invoker（调用者角色）：负责调用命令对象执行请求，通常会持有命令对象（可以持有多个命令对象）。Invoker是Client真正触发命令并要求命令执行相应操作的地方（使用命令对象的入口）。
 * Receiver（接收者角色）：是真正执行命令的对象。任何类都可能成为一个接收者，只要它能够实现命令要求实现的相应功能。
 * Client（客户端角色）：Client可以创建具体的命令对象，并且设置命令对象的接收者。
 *
 * JDK:
 * Command:
 * 把一个或一些命令封装到一个对象中。
 *
 * java.lang.Runnable
 * javax.swing.Action
 */
public class Client {
    public static void main(String[] args) {
        ManReceiver receiver = new ManReceiver();
        ServerInvoker invoker = new ServerInvoker();

        invoker.setCommand(new MoveRightCommand(receiver, 10));
        invoker.execute();
        invoker.execute();
        invoker.undo();

        invoker.setCommand(new MoveLeftCommand(receiver, 10));
        invoker.execute();
        invoker.undo();
        invoker.undo();
    }
}
