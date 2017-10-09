package com.baofu.crossborder.concurrency.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description：
 * <p>
 * #
 * </p >
 * User: qingyunzi Date: 17-10-9 ProjectName:servletDemo Version:
 */
public class ReentrantReadWriteLockTest {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();

        new Thread() {
            @Override
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();
    }


    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 100000) {
                Thread.sleep(100);
                System.out.println(thread.getName() + " in reading processing");
            }
            System.out.println(thread.getName() + " reading is over");
        } catch (Exception e) {

        } finally {
            rwl.readLock().unlock();
        }

    }
}