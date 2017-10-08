package com.baofu.crossborder.concurrent;

public class Main {
    public static void main(String[] args) {
        try {

            MyThread thread=new MyThread();
            thread.start();
            thread.interrupt();
            System.out.println(thread.interrupted());
            System.out.println(thread.isInterrupted());
            Thread.sleep(1000);
            Thread.currentThread().interrupt();
            System.out.println(Thread.interrupted());


        } catch (Exception e) {

        }
        System.out.println("end!");
    }
}
