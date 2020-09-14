package com.kkd.study.thread;

/**
 * Created by Kideok Kim on 23/06/2018.
 */
public class RunnableTask implements Runnable {
    private int id;

    public RunnableTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Runnable Task ID: " + id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
