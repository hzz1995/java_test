package com.huzz.concurrent;

import java.util.concurrent.CountDownLatch;


/***
 * *CountDownLatch和CyclicBarrier区别：
 * 1.countDownLatch是一个计数器，线程完成一个记录一个，计数器递减，只能只用一次
 * 2.CyclicBarrier的计数器更像一个阀门，需要所有线程都到达，然后继续执行，计数器递增，提供reset功能，可以多次使用
 */
public class CountDownlatchTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始" + "," + Thread.currentThread().getName());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new readNum(i, countDownLatch)).start();
        }
        countDownLatch.await();//await的作用就是当前线程等待latch.countDown()直到减到0
        System.out.println("线程执行结束" + "," + Thread.currentThread().getName());
    }
 
    static class readNum implements Runnable {
        private int id;
        private CountDownLatch latch;
 
        public readNum(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }
 
        @Override
        public void run() {
            synchronized (this) {
                System.out.println("id:" + id + "," + Thread.currentThread().getName());
                latch.countDown();
                System.out.println("线程组任务" + id + "," + Thread.currentThread().getName() + "结束，其他任务继续");
            }
        }
    }
}