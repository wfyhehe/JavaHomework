package com.wfy.review;

import java.util.Date;

/**
 * Created by wfy on 2018/5/13, good luck.
 */
public class Test6 implements Runnable {
    private Thread clocker = null;
    private Date now = new Date();

    public Test6() {
        clocker = new Thread(this);
        clocker.start();
    }

    public static void main(String[] args) {
        new Test6();
    }

    @Override
    public void run() {
        System.out.println("run");
        while(true) {
            now = new Date();
            System.out.println(now);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
