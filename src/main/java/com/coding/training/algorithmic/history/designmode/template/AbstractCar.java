package com.coding.training.algorithmic.history.designmode.template;

//悍马模型抽象类
public abstract class AbstractCar{
    //模型发动
    protected abstract void start();
    //模型能停止
    protected abstract void stop();
    //模型喇叭会响
    protected abstract void alarm();
    //引擎会响
    protected abstract void engineBoom();
    //模型会跑
    public final void run() {
        //先发动汽车
        this.start();
        //引擎开始轰鸣
        this.engineBoom();
        //跑的过程中遇到一条狗,于是按喇叭
        this.alarm();
        //到达目的地停车
        this.stop();
    }
}
