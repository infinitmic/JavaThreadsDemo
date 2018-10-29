package com.wamiraj.jtd.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskA implements Runnable {

    private static int count = 0;
    private int taskId;

    public LoopTaskA() {
        this.taskId = ++count;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> STARTING ####");

        for(int index = 10; index > 0; index--) {
            System.out.println("#### [" + currentThreadName + "] <" + taskId + "> TICK TICK " + index);

            try{
                TimeUnit.MILLISECONDS.sleep((long)Math.random()*3000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> DONE ####");
    }
}
