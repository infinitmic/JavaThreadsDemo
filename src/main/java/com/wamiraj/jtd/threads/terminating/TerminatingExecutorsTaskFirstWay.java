package com.wamiraj.jtd.threads.terminating;

import com.wamiraj.jtd.common.FactorialTaskA;
import com.wamiraj.jtd.common.LoopTaskE;
import com.wamiraj.jtd.common.NamedThreadsFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * The type Terminating executors task first way.
 */
public class TerminatingExecutorsTaskFirstWay {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     *             {@link FactorialTaskA}
     * @throws InterruptedException the interrupted exception
     * @see InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadsFactory());

        LoopTaskE taskE = new LoopTaskE();
        FactorialTaskA factorialTaskA = new FactorialTaskA(30,1000);

        executorService.submit(taskE);
        executorService.submit(factorialTaskA);

        executorService.shutdown();

        TimeUnit.MILLISECONDS.sleep(3000);

        taskE.cancel();
        factorialTaskA.cancel();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
