package com.test.jinke.blockingQueue;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
* @ClassName: MyLockingQueue  
* <p>Description: 阻塞队列简单实现   </p>
* @author  frankman
* @date 2019年6月1日 上午10:54:42  
*
 */
public class MyLockingQueue {

	private LinkedList<Object> list = new LinkedList<Object>();
	//计数器 保证原子性 
	private AtomicInteger count = new AtomicInteger();
	public int maxCount;
	private int minCount;
	private Object lock = new Object();

	public MyLockingQueue(int num) {
		this.maxCount = num;
		this.minCount = 0 ;
	}
	
	public int getSize(){
		return count.get();
	}

	public void put(Object obj) {
		synchronized (lock) {
			if (count.get()==this.maxCount ) {
				try {
					System.out.println("put数据阻塞");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(obj);
			count.incrementAndGet();
			lock.notify();
		}

	}

	public Object take() {
		Object ret = null;
		synchronized (lock) {
			while(this.minCount==count.get()){
				try {
					System.out.println("take数据阻塞");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			ret = list.removeFirst();
			count.decrementAndGet();
			//通知等待锁 
			lock.notify();
		}
		return ret;
	}
	
	public static void main(String[] args) {
		
		final MyLockingQueue queue = new MyLockingQueue(3);
		queue.put("3");
		queue.put("4");
		queue.put("44");
		System.out.println("当前容器的长度:" + queue.getSize());
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run(){
				queue.put("33");
				System.out.println("放入数据 33 当前容器的长度:" + queue.getSize());
			}
		});
		t1.start();
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run(){
				Object obj = queue.take();
				System.out.println("容器中获取的数据："+obj);
			}
		});
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}
}
