package com.test.productconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wujuhong on 2019/7/3.
 * 传统版的生产者消费者模式
 */
public class ProductConsumer_TraditionDemo {
 
    public static void main(String[] args) {
        final ShareData shareData = new ShareData();
       /* new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();*/
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				 for (int i = 0; i < 5; i++) {
		                try {
		                    shareData.increment();
		                } catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
		            }
				
			}
		},"AA").start();
 
 
        /*new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();*/
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				 for (int i = 0; i < 5; i++) {
		                try {
		                    shareData.decrement();
		                } catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
		            }
				
			}
		},"BB").start();
    }
}
 
class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //1、判断
            while (number != 0) {
                //等待，不能生产
                condition.await();
            }
            //2、干活
            number++;
            System.out.println(Thread.currentThread().getName() + " " + number);
            //3、通知唤醒
            condition.signalAll();
 
        } finally {
            lock.unlock();
        }
    }
 
 
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //1、判断
            while (number == 0) {
                //等待，不能生产
                condition.await();
            }
            //2、干活
            number--;
            System.out.println(Thread.currentThread().getName() + " " + number);
            //3、通知唤醒
            condition.signalAll();
 
        } finally {
            lock.unlock();
        }
    }
}