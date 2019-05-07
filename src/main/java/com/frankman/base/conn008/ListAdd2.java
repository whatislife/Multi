package com.frankman.base.conn008;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * 
* @ClassName: ListAdd2  
* <p>Description: 线程间通信基础版本升级 几种方式 
* 1.使用wait notify 和synchronized 配合使用 wait释放lock锁 notify不释放锁 
* 2.CountDownLatch做操作 
* https://www.baidu.com/link?url=bQLDt5r-VYz3-_s57DWkzjJ8gSAPOAR5_FClT0M7SGxyZEURasQ3KNStc1gJo_hg&wd=&eqid=f9df43da00085f99000000065cd0e0f8
*   </p>
* @date 2019年5月7日 上午9:32:00  
*
 */
public class ListAdd2 {
	private volatile static List list = new ArrayList();	
	
	public void add(){
		list.add("bjsxt");
	}
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		
		final ListAdd2 list2 = new ListAdd2();
		
		// 1 实例化出来一个 lock
		// 当使用wait 和 notify 的时候 ， 一定要配合着synchronized关键字去使用
		//final Object lock = new Object();
		
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//synchronized (lock) {
						for(int i = 0; i <10; i++){
							list2.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if(list2.size() == 5){
								System.out.println("已经发出通知..");
								countDownLatch.countDown();
								//lock.notify();
							}
						}						
					//}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				//synchronized (lock) {
					if(list2.size() != 5){
						try {
							//System.out.println("t2进入...");
							//lock.wait();
							countDownLatch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
					throw new RuntimeException();
				//}
			}
		}, "t2");	
		
		//前后是有顺序的 
		t2.start();
		t1.start();
		
	}
	
}
