package com.wamiraj.jtd.threads.terminating;

import com.wamiraj.jtd.common.LoopTaskE;

import java.util.concurrent.TimeUnit;

public class TerminatingThreadFirstWay {

    public static void main(String[] args) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        LoopTaskE taskE1 = new LoopTaskE();
        LoopTaskE taskE2 = new LoopTaskE();
        LoopTaskE taskE3 = new LoopTaskE();

        new Thread(taskE1, "Task 1").start();
        new Thread(taskE2, "Task 2").start();
        new Thread(taskE3, "Task 3").start();

        TimeUnit.MILLISECONDS.sleep(3000);

        taskE1.cancel();
        taskE2.cancel();
        taskE3.cancel();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
