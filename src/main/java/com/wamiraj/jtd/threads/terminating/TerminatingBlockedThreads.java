package com.wamiraj.jtd.threads.terminating;

import com.wamiraj.jtd.common.LoopTaskG;
import com.wamiraj.jtd.common.LoopTaskH;

import java.util.concurrent.TimeUnit;

public class TerminatingBlockedThreads {

    public static void main(String[] args) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        LoopTaskG taskG1 = new LoopTaskG();
        LoopTaskG taskG2 = new LoopTaskG();
        LoopTaskG taskG3 = new LoopTaskG();
        LoopTaskH taskH1 = new LoopTaskH();
        LoopTaskH taskH2 = new LoopTaskH();

        Thread t1 = new Thread(taskG1, "MyThread1");
        t1.start();

        Thread t2 = new Thread(taskG2, "MyThread2");
        t2.start();

        Thread t3 = new Thread(taskG3, "MyThread3");
        t3.start();

        Thread t4 = new Thread(taskH1, "MyThread4");
        t4.start();

        Thread t5 = new Thread(taskH2, "MyThread5");
        t5.start();


        TimeUnit.MILLISECONDS.sleep( 1500);

        System.out.println("[" + currentThreadName + "] Interrupting..." + t1.getName() + "....");
        t1.interrupt();

        System.out.println("[" + currentThreadName + "] Interrupting..." + t2.getName() + "....");
        t2.interrupt();

        System.out.println("[" + currentThreadName + "] Interrupting..." + t3.getName() + "....");
        t3.interrupt();

        System.out.println("[" + currentThreadName + "] Interrupting..." + t4.getName() + "....");
        t4.interrupt();

        System.out.println("[" + currentThreadName + "] Interrupting..." + t5.getName() + "....");
        t5.interrupt();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
