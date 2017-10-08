package com.baofu.crossborder.concurrent;


class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 50; i++) {
            System.out.println("i=" + (i + 1));
        }
    }
}