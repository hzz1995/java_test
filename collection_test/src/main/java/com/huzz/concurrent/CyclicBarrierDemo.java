package com.huzz.concurrent;

import java.util.concurrent.CyclicBarrier;

/***
 * 1. CAS（Compare and Swap）
 * CAS是一种无锁算法。有3个操作数：内存值V、旧的预期值A、要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。
 *
 * 2. AQS队列
 * AQS是一个用于构建锁和同步容器的框架。
 *
 * AQS使用一个FIFO的队列（也叫CLH队列，是CLH锁的一种变形），表示排队等待锁的线程。队列头节点称作“哨兵节点”或者“哑节点”，
 * 它不与任何线程关联。其他的节点与等待线程关联，每个节点维护一个等待状态waitStatus。
 */
public class CyclicBarrierDemo {

    static class TaskThread extends Thread {
        
        CyclicBarrier barrier;
        
        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 到达栅栏 A");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 A");
                
                Thread.sleep(2000);
                System.out.println(getName() + " 到达栅栏 B");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
            
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
            }
        });
        
        for(int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }

        barrier.reset();
    }
    
}