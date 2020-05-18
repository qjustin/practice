package com.coding.training.concurrency.thread;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerUsage {

    public static void main(String[] args) {
        test2();
    }

    /**
     * 第一种用法
     * 延时1000毫秒后执行定时方法
     */
    public static void test1() {
        //创建Timer
        final Timer timer = new Timer();
        //设定定时任务
        timer.schedule(new TimerTask() {
            //定时任务执行方法
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }, 1000);
    }

    /**
     * 第二种用法
     * 延时1000毫秒后,每隔1000执行一次定时方法
     */
    public static void test2() {
        //创建Timer
        final Timer timer = new Timer();
        //设定定时任务
        timer.schedule(new TimerTask() {
            //定时任务执行方法
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }, 1000, 3000);
    }


    /**
     * 第三种用法
     * 在某个固定时间点执行任务
     */
    public static void test3() {
        Calendar ca = Calendar.getInstance();

        //设置小时
        ca.set(Calendar.HOUR_OF_DAY, 23);
        //设置分钟
        ca.set(Calendar.MINUTE, 50);
        //设置秒
        ca.set(Calendar.SECOND, 00);

        //创建Timer
        final Timer timer = new Timer();
        //设定定时任务
        timer.schedule(new TimerTask() {
            //定时任务执行方法
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }, ca.getTime());
    }

    /**
     * 第四种用法
     * 在某个固定时间点，按照固定频率执行
     * (如在每天的凌晨执行一次)
     */
    public static void test4() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 24);
        ca.set(Calendar.MINUTE, 00);
        ca.set(Calendar.SECOND, 00);

        //创建Timer
        final Timer timer = new Timer();
        //设定定时任务
        timer.schedule(new TimerTask() {
            //定时任务执行方法
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }, ca.getTime(), 1000 * 60 * 60 * 24);    //每天凌晨执行一次
    }
}
