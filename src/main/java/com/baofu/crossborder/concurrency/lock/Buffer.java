package com.baofu.crossborder.concurrency.lock;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description：
 * <p>
 * #
 * </p >
 * User: qingyunzi Date: 17-10-9 ProjectName:servletDemo Version:
 */
public class Buffer {

    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;
    private int maxSize;
    private List<Date> storage;

    Buffer(int size) {
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
        maxSize = size;
        storage = new LinkedList<Date>();
    }


    public void put() {
        lock.lock();
        try {
            while (storage.size() == maxSize) {
                System.out.println(Thread.currentThread().getName() + ": wait\n");
                notFull.await();
            }
            storage.add(new Date());
            System.out.println(Thread.currentThread().getName() + ": put:" + storage.size() + "\n");
            Thread.sleep(1000);
            notEmpty.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void take() {
        lock.lock();
        try {
            while (storage.size() == 0) {
                System.out.println(Thread.currentThread().getName() + ": wait \n");
                notEmpty.await();
            }
            Date d = ((LinkedList<Date>) storage).poll();
            System.out.println(Thread.currentThread().getName() + ": take:" + storage.size()+"\n");
            Thread.sleep(1000);
            notFull.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}



