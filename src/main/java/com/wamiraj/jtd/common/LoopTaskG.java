package com.wamiraj.jtd.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskG implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;


    public LoopTaskG() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskG" + instanceNumber;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> STARTING ####");

        for(int index = 1;; index++) {
            System.out.println("#### [" + currentThreadName + "] <" + taskId + "> TICK TICK " + index);

            try{
                TimeUnit.MILLISECONDS.sleep(3000);
            }catch (InterruptedException e) {
                System.out.println("#### [" + currentThreadName + "] <" + taskId + "> Interrupted! Cancelling " + index);
                break;
            }
        }

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> DONE ####");
    }
}
