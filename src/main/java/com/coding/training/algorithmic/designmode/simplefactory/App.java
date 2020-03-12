package com.coding.training.algorithmic.designmode.simplefactory;

/**
 * 应用场景
 * 生成复杂对象时，确定只有一个工厂类，可以使用简单工厂模式。否则有多个工厂类的话，使用工厂方法模式。
 * 6.优点
 * 代码解耦，创建实例的工作与使用实例的工作分开，使用者不必关心类对象如何创建。
 * 7.缺点
 * 违背开放封闭原则，若需添加新产品则必须修改工厂类逻辑，会造成工厂逻辑过于复杂。
 * 简单工厂模式使用了静态工厂方法，因此静态方法不能被继承和重写。
 * 工厂类包含了所有实例（产品）的创建逻辑，若工厂类出错，则会造成整个系统都会会受到影响。
 * 8.工厂方法模式与简单工厂模式比较
 * 工厂方法模式有抽象工厂类，简单工厂模式没有抽象工厂类且其工厂类的工厂方法是静态的。
 * 工厂方法模式新增产品时只需新建一个工厂类即可，符合开放封闭原则；而简单工厂模式需要直接修改工厂类，违反了开放封闭原则。
 *
 * 由于简单工厂模式新增产品时需要直接修改工厂类，违反了开放封闭原则。因此可以使用反射来创建实例对象，确保能够遵循开放封闭原则。
 *
 * Factory:
 * 简单来说，按照需求返回一个类型的实例。
 * java.lang.Proxy#newProxyInstance()
 */
public class App {
    public static void main(String[] args) {
        // 未判断输入异常，可以用枚举类型代替字符串
        CarFactory.create("bmw").name();
        CarFactory.create("auto").name();

        // 由于简单工厂模式新增产品时需要直接修改工厂类，违反了开放封闭原则。因此可以使用反射来创建实例对象，确保能够遵循开放封闭原则。
        RefectCarFactory.create(AutoCarProduct.class).name();
        RefectCarFactory.create(BMWCarProduct.class).name();
    }
}
