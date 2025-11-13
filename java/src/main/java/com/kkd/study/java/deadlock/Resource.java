package com.kkd.study.java.deadlock;


public class Resource  {
    public static void main(String[] args) {
      Resource r1 = new Resource();
      Resource r2 = new Resource();
    
        Thread t1 = new Thread(() -> r1.use(r2), "Thread-1");
        Thread t2 = new Thread(() -> r2.use(r1), "Thread-2");
        t1.start();
        t2.start();
    }

    public synchronized void use(Resource other) {
        System.out.println(Thread.currentThread().getName() + " is using this resource.");
        try { 
          Thread.sleep(100); 
        } catch (InterruptedException e) {}
        
        other.finish();
    }

    public synchronized void finish() {
      System.out.println(Thread.currentThread().getName() + " finished using resource.");
    }
}
