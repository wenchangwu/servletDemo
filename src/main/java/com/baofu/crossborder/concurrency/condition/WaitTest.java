package com.baofu.crossborder.concurrency.condition;

import java.util.PriorityQueue;

/**
 * Description：
 * <p>
 * #
 * </p >
 * User: qingyunzi Date: 17-10-9 ProjectName:servletDemo Version:
 */
public class WaitTest {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

    public static void main(String[] args) {
        WaitTest waitTest = new WaitTest();
        Produce produce = waitTest.new Produce();
        Consume consume = waitTest.new Consume();

        produce.start();
        consume.start();

    }


    class Consume extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            Thread.sleep(1000);
                            System.out.println(Thread.currentThread().getName() + " the queue is empty");
                            queue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                            queue.notify();
                        }

                    }
                    queue.poll();
                    queue.notify();
                    System.out.println("get value from queue,the queue size is :" + queue.size());
                }
            }
        }
    }

    class Produce extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == queueSize) {
                        try {
                            Thread.sleep(1000);
                            System.out.println(Thread.currentThread().getName() + " the queue is full");
                            queue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.offer(1);
                    queue.notify();
                    System.out.println("insert value into queue the size left is :" + (queueSize - queue.size()));
                }
            }
        }
    }
}