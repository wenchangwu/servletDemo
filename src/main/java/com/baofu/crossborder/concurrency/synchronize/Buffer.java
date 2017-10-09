package com.baofu.crossborder.concurrency.synchronize;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Description：
 * <p>
 * #
 * </p >
 * User: qingyunzi Date: 17-10-9 ProjectName:servletDemo Version:
 */
public class Buffer {

    private int maxSize;
    private List<Date> storage;

    Buffer(int size) {
        maxSize = size;
        storage = new LinkedList<Date>();
    }

    public synchronized void put() {
        try {
            while(storage.size()==maxSize){
                System.out.println(Thread.currentThread().getName()+":wait \n");
                wait();
            }
            storage.add(new Date());
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
            notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public synchronized void take(){
        try{
            while(storage.size()==0){
                System.out.println(Thread.currentThread().getName() +":wait\n");
                wait();
            }
            Date d=((LinkedList<Date>)storage).poll();
            System.out.println(Thread.currentThread().getName() +":take\n"+storage.size());
            Thread.sleep(1000);
            notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}