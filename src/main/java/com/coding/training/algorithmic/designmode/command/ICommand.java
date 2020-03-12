package com.coding.training.algorithmic.designmode.command;

/**
 *  客户端(Client)角色：创建请求者，接收者以及命令对象，执行具体逻辑。
 *  命令(Command)角色：声明了一个给所有具体命令类的抽象接口。
 *  具体命令(ConcreteCommand)角色：定义一个接收者和行为之间的弱耦合；实现execute()方法，负责调用接收者的相应操作。execute()方法通常叫做执行方法。
 *  请求者(Invoker)角色：负责调用命令对象执行请求，相关的方法叫做行动方法。
 *  接收者(Receiver)角色：负责具体实施和执行一个请求。任何一个类都可以成为接收者，实施和执行请求的方法叫做行动方法。
 *
 * 优点：
 * 实现客户端和接受者之间的解耦。
 * 可以动态的添加新的命令。
 * 只需要调用同一个方法（doCommand方法）便可以实现不同的功能。
 *
 * 缺点：
 * 实现一个具体的命令系统，可能要创建很多的具体命令对象。
 */
public interface ICommand {
    void execute();
    void undo();
}
