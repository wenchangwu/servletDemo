package com.baofu.crossborder.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description：
 * <p>
 * #
 * </p >
 * User: qingyunzi Date: 17-10-9 ProjectName:servletDemo Version:
 */
public class LockInterruptTest {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptTest test = new LockInterruptTest();
        MyThread thread0 = new MyThread(test);
        MyThread thread1 = new MyThread(test);
        thread0.start();
        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread1.interrupt();
    }

    public void insert(Thread thread)throws InterruptedException{
        lock.lockInterruptibly();
        try{
           System.out.println(thread.getName()+" get lock");
           long startTime=System.currentTimeMillis();
           for(;;){
               if(System.currentTimeMillis()-startTime>=Integer.MAX_VALUE){
                   break;
               }
           }
        }catch (Exception e){

        }finally {
            System.out.println(Thread.currentThread().getName()+" execute finally");
            lock.unlock();
            System.out.println(thread.getName()+" release lock");
        }
    }


}