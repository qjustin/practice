package com.coding.training.algorithmic.history.designmode.observer;

/**
 * 允许一个对象向所有的侦听的对象广播自己的消息或事件。
 *
 * java.util.EventListener
 * javax.servlet.http.HttpSessionBindingListener
 * javax.servlet.http.HttpSessionAttributeListener
 * javax.faces.event.PhaseListener
 */
public class App {
    public static void main(String[] args) {
        IObservable postman = new Postman();

        IObserver jess = new JessObserver();
        IObserver tina = new TinaObserver();

        postman.register(jess);
        postman.register(tina);

        postman.notify("快递到了,请下楼领取.");
    }
}
