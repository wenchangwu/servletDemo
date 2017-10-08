package com.baofu.crossborder.concurrent;

public class InterruptTest {

    public static void main(String[] args) {
        InterruptTest interruptTest = new InterruptTest();
        Thread t = new Thread(interruptTest.runnable);
        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t.interrupt();
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int i = 0;
            try {
                while (i < 1000) {
                    Thread.sleep(500);
                    System.out.println(i++);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
