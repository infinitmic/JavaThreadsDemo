package com.wamiraj.jtd.threads.terminating;

import com.wamiraj.jtd.common.LoopTaskE;
import com.wamiraj.jtd.common.LoopTaskF;

import java.util.concurrent.TimeUnit;

public class TerminatingThreadSecondWay {

    public static void main(String[] args) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        LoopTaskF taskF1 = new LoopTaskF();
        LoopTaskF taskF2 = new LoopTaskF();
        LoopTaskF taskF3 = new LoopTaskF();

        Thread t1 = new Thread(taskF1, "MyThread1");
        t1.start();

        Thread t2 = new Thread(taskF2, "MyThread2");
        t2.start();

        Thread t3 = new Thread(taskF3, "MyThread3");
        t3.start();


        TimeUnit.MILLISECONDS.sleep( 3000);

        System.out.println("[" + currentThreadName + "] Interrupting..." + t1.getName() + "....");
        t1.interrupt();

        System.out.println("[" + currentThreadName + "] Interrupting..." + t2.getName() + "....");
        t2.interrupt();

        System.out.println("[" + currentThreadName + "] Interrupting..." + t3.getName() + "....");
        t3.interrupt();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
