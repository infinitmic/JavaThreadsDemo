package com.wamiraj.jtd.common;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialTaskA implements Callable<Long> {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private volatile boolean isShutDown = false;

    private long a;
    private long sleepTime;
    private long factorial;

    public FactorialTaskA(long a, long sleepTime) {
        this.a = a;
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "FactorialTaskA" + instanceNumber;
    }

    @Override
    public Long call() throws Exception {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> STARTING ####");

        factorial = 1L;

        for (int index = 1; index < a; index++) {

            factorial *= index;
            System.out.println("#### [" + currentThreadName + "] <" + taskId + "> Iteration " + index
                    + ". Intermediate Result = " + factorial);

            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                if (isShutDown) {
                    factorial = -1L;
                    break;
                }
            }
        }

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> DONE ####");

        return factorial;
    }

    public void cancel() {
        System.out.println("#### [" + Thread.currentThread().getName() + "] <" + taskId + "> SHUTTING DOWN ####");

        synchronized (this) {
            this.isShutDown = true;
        }
    }
}
