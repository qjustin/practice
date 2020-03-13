package com.coding.training.algorithmic.history.designmode.decorator;

/**
 * 角色说明：
 * Component（抽象组件）：接口或者抽象类，被装饰的最原始的对象。具体组件与抽象装饰角色的父类。
 * ConcreteComponent（具体组件）：实现抽象组件的接口。
 * Decorator（抽象装饰角色）：一般是抽象类，抽象组件的子类，同时持有一个被装饰者的引用，用来调用被装饰者的方法;同时可以给被装饰者增加新的职责。
 * ConcreteDecorator（具体装饰类）：抽象装饰角色的具体实现。
 *
 *  Decorator:
 * 为一个对象动态的加上一系列的动作，而不需要因为这些动作的不同而产生大量的继承类。这个模式在JDK中几乎无处不在，所以，下面的列表只是一些典型的。
 *
 * java.io.BufferedInputStream(InputStream)
 * java.io.DataInputStream(InputStream)
 * java.io.BufferedOutputStream(OutputStream)
 * java.util.zip.ZipOutputStream(OutputStream)
 * java.util.Collections#checked[List|Map|Set|SortedSet|SortedMap]()
 */
public class App {

    public static void main(String[] args) {
        IRoom room = new Room();//有一间新房间
        IRoom bedroom = new Bedroom(room);
        bedroom.fitment();//装修成卧室
        IRoom kitchen = new Kitchen(room);
        kitchen.fitment();//装修成厨房
    }
}
