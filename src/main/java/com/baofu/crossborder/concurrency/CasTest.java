package com.baofu.crossborder.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CasTest {
    private AtomicInteger atomicI=new AtomicInteger(0);
    private int i =0;

    public static void main(String[] args){
        final CasTest cas=new CasTest();
        List<Thread> ts=new ArrayList<Thread>(100);
        long start=System.currentTimeMillis();
        for(int j=0;j<100;j++){
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<10000;i++){
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }

        for(Thread t:ts){
            t.start();
        }


        for(Thread t:ts){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis()-start);
    }


    private void safeCount(){
        for(;;){
            int i=atomicI.get();
            boolean suc=atomicI.compareAndSet(i,++i);
            if(suc){
                break;
            }
        }
    }


    private void count(){
        i++;
    }
}
