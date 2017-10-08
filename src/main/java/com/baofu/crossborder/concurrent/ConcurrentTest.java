package com.baofu.crossborder.concurrent;

public class ConcurrentTest {

    private static long count = 100000000000L;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new Runnable() {
            public void run() {
                long a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        t.start();
        long b = 0;
        for (long i = 0; i < b; b++) {
            b += 5;
        }
        t.join();
        long time = System.currentTimeMillis() - startTime;
        System.out.println("concurrency time elapse:" + time);
    }

    private static void serial() {
        long startTime = System.currentTimeMillis();
        long a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        long b = 0;
        for (long i = 0; i < b; b++) {
            b += 5;
        }
        long time = System.currentTimeMillis() - startTime;
        System.out.println("serial time elapse:" + time);
    }
}
