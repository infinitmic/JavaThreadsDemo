package com.wamiraj.jtd.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskH implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private volatile boolean isSleepInterrupted = false;

    public LoopTaskH() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskH" + instanceNumber;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> STARTING ####");

        for (int index = 1; ; index++) {
            System.out.println("#### [" + currentThreadName + "] <" + taskId + "> TICK TICK " + index);

            try {
                TimeUnit.MILLISECONDS.sleep((long) Math.random() * 3000);
            } catch (InterruptedException e) {
                System.out.println("#### [" + currentThreadName + "] <" + taskId + "> Sleep Interrupted.. Setting the flag");
                isSleepInterrupted = true;
            }

            doSomeMoreWork();

            if (isSleepInterrupted || Thread.interrupted()) {
                System.out.println("#### [" + currentThreadName + "] <" + taskId + "> Interrupted..Cancelling");
                break;
            }
        }

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> DONE ####");
    }

    private void doSomeMoreWork() {
        System.out.println("#### [" + Thread.currentThread().getName() + "] <" + taskId + "> Doing some more work...");
    }
}
