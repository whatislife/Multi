package com.frankman.base.conn009;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 *  
* @ClassName: MyQueue  
* <p>Description: 队列阻塞队列  put take 放入和取走  进行阻塞队里操作 
* 1.需要一些集合
* 2.计数器 自增和自减
* 3.上线max容量容器
* 4.添加一把锁 
* 
*  AtomicInteger 保证原子操作，添加volatile 实现synchronized但是又没有被锁定 
*  https://www.cnblogs.com/rever/p/8215743.html
*  
*  面试题：写个双重锁检查单例模式，然后volatile关键词会被问
*  https://my.oschina.net/yukong/blog/870890
*  http://www.cnblogs.com/dolphin0520/p/3920373.html
*  
*  volatile 
*  (1)可见性
*  (2)禁止指令重排 
*  
*  非常好的博客
*  http://www.cnblogs.com/dolphin0520/p/3920373.html 
*  
*  </p>
* @date 2019年5月11日 上午11:27:27  
*
 */
public class MyQueue {
	
	//1 需要一个承装元素的集合 
	private LinkedList<Object> list = new LinkedList<Object>();
	
	//2 需要一个计数器
	private AtomicInteger count = new AtomicInteger(0);
	
	//3 需要制定上限和下限
	private final int minSize = 0;
	
	private final int maxSize ;
	
	//4 构造方法
	public MyQueue(int size){
		this.maxSize = size;
	}
	
	//5 初始化一个对象 用于加锁
	private final Object lock = new Object();
	
	
	//put(anObject): 把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续.
	public void put(Object obj){
		synchronized (lock) {
			while(count.get() == this.maxSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//1 加入元素
			list.add(obj);
			//2 计数器累加
			count.incrementAndGet();
			//3 通知另外一个线程（唤醒）
			lock.notify();
			System.out.println("新加入的元素为:" + obj);
		}
	}
	
	
	//take: 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入.
	public Object take(){
		Object ret = null;
		synchronized (lock) {
			while(count.get() == this.minSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//1 做移除元素操作
			ret = list.removeFirst();
			//2 计数器递减
			count.decrementAndGet();
			//3 唤醒另外一个线程
			lock.notify();
		}
		return ret;
	}
	
	public int getSize(){
		return this.count.get();
	}
	
	
	public static void main(String[] args) {
		
		final MyQueue mq = new MyQueue(5);
		mq.put("a");
		mq.put("b");
		mq.put("c");
		mq.put("d");
		mq.put("e");
		
		System.out.println("当前容器的长度:" + mq.getSize());
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mq.put("f");
				mq.put("g");
			}
		},"t1");
		
		t1.start();
		
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Object o1 = mq.take();
				System.out.println("移除的元素为:" + o1);
				Object o2 = mq.take();
				System.out.println("移除的元素为:" + o2);
			}
		},"t2");
		
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t2.start();
		
		
	}
	
	
	
}
