package com.wamiraj.jtd.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskE implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private volatile boolean isShutDown = false;

    public LoopTaskE() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskE" + instanceNumber;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> STARTING ####");

        for(int index = 1;; index++) {
            System.out.println("#### [" + currentThreadName + "] <" + taskId + "> TICK TICK " + index);

            try{
                TimeUnit.MILLISECONDS.sleep((long)Math.random()*3000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                if(isShutDown) {
                    break;
                }
            }
        }

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> DONE ####");
    }

    public void cancel() {
        System.out.println("#### [" + Thread.currentThread().getName() + "] <" + taskId + "> SHUTTING DOWN ####");

        synchronized (this) {
            this.isShutDown = true;
        }
    }
}
