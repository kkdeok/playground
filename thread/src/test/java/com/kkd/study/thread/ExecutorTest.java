package com.kkd.study.thread;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * Created by Kideok Kim on 23/06/2018.
 */
public class ExecutorTest {
    private static int NUM = 1;

    @Test
    public void testFixedThreadPool() {
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(new RunnableTask(1));
        service.submit(new RunnableTask(2));
        service.submit(new RunnableTask(3));
        service.shutdown();
        try {
            service.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Integer getCount() {
        return NUM++;
    }

    @Ignore
    public void testFixedThreadPoolWithFuture() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Callable<Integer> task = this::getCount;

        Future future1 = service.submit(task);
        Future future2 = service.submit(task);
        Future future3 = service.submit(task);

        try {
            Integer result1 = (Integer) future1.get();
            Integer result2 = (Integer) future2.get();
            Integer result3 = (Integer) future3.get();

            assertTrue(result1 == 1);
            assertTrue(result2 == 2);
            assertTrue(result3 == 3);

            service.shutdown();
            service.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCachedThreadPool() {
        // CachedThreadPool doesn't get thread num as a parameter.
        // It creates new thread as need.
        // It will reuse previously constructed threads when they are available.
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new RunnableTask(1));
        service.submit(new RunnableTask(2));
        service.submit(new RunnableTask(3));

        service.shutdown();
    }

    @Test
    public void testScheduledExecutorService() {
        // count means how many time we want to run scheduled executor.
        long count = 2;
        long period = 1000;
        long initialDelay = 0;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(
                new RunnableTask(1)
                , initialDelay
                , period
                , TimeUnit.MILLISECONDS);
        try {
            // scheduled executor will be executed by count.
            Thread.sleep(count * period);
            scheduler.shutdown();
            scheduler.awaitTermination(1000, TimeUnit.MILLISECONDS);
            System.out.println("scheduler is shutdown");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testListeningExecutorService() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newWorkStealingPool());

    }
}