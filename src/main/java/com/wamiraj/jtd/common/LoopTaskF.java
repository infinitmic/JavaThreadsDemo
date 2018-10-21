package com.wamiraj.jtd.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LoopTaskF implements Runnable {

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private static int DATA_SIZE = 10000;

    public LoopTaskF() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskF" + instanceNumber;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> STARTING ####");

        for (int index = 1; ; index++) {
            System.out.println("#### [" + currentThreadName + "] <" + taskId + "> TICK TICK " + index);

            doSomeWork();

            if (Thread.interrupted()) {
                System.out.println("#### [" + currentThreadName + "] <" + taskId + "> Interrupted! Cancelling....");
                break;
            }

        }

        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> Thread Interrupted ...");
        System.out.println("#### [" + currentThreadName + "] <" + taskId + "> DONE ####");
    }

    private void doSomeWork() {
        for (int i = 0; i < 2; i++) {
            Collections.sort(generateDataSet());
        }
    }

    private List<Integer> generateDataSet() {
        List<Integer> list = new ArrayList<>();
        Random randomGenerator = new Random();

        for (int index = 0; index < DATA_SIZE; index++) {
            list.add(randomGenerator.nextInt(DATA_SIZE));
        }
        return list;
    }
}
