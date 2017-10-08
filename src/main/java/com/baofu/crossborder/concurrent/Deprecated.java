package com.baofu.crossborder.concurrent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Deprecated {
    public static void main(String [] args) throws InterruptedException {
        DateFormat df=new SimpleDateFormat("HH:mm:ss");
        Thread printThread=new Thread(new Runner(),"PrintThread");
        printThread.setDaemon(true);
        printThread.start();
        TimeUnit.SECONDS.sleep(3);
        printThread.suspend();
        System.out.println("main suspend printThread at "+df.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        printThread.resume();
        System.out.println("main resume printThread at "+df.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        printThread.stop();
        System.out.println("main stop printThread at "+df.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
    }


    static class Runner implements Runnable{
        public void run() {
            DateFormat df=new SimpleDateFormat("HH:mm:ss");
            while(true){
                System.out.println(Thread.currentThread().getName()+"Run at"+df.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
