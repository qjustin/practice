package com.coding.training.algorithmic.history.designmode.template;

/*
 * 电脑开机的例子
 * 1. powerOn
 * 2. checkHardware
 * 3. loadOS
 * 4. sysLogin
 * 5. launch
 *
 * 允许子类重载部分父类而不需要完全重写。
 * java.util.Collections#sort()
 * java.io.InputStream#skip()
 * java.io.InputStream#read()
 * java.util.AbstractList#indexOf()
 *
 */
public class Factory {
    public static void main(String[] args) {
        AbstractCar car = new BMWCar();
        car.run();
    }
}
