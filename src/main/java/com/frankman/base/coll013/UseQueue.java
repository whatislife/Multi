package com.frankman.base.coll013;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 
* @ClassName: UseQueue  
* <p>Description: 
两种
1.ConcurrentLinkedQueue 性能高非阻塞的
(1)高并发场景下 无界限 线程安全 先进先出的原则 
(2)不允许null 头是先进入的 尾是后入的
add offer 没有区别都是插入数据的 
poll获取出来删除 peek获取出来不删除
2.ArrayBlockingQueue 阻塞的
五种实现
(1)arrayBlockingQueue
(2)LinkedBlockingQUeue
(3)PriorityBlockingQueue
(4)DelayQueue
(5)SynchronizedQueue
</p>
* @date 2019年5月11日 下午4:42:32  
*
 */
public class UseQueue {

	public static void main(String[] args) throws Exception {
		
		//高性能无阻塞无界队列：ConcurrentLinkedQueue
		ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.add("e");
		
		System.out.println(q.poll());	//a 从头部取出元素，并从队列里删除
		System.out.println(q.size());	//4
		System.out.println(q.peek());	//b
		System.out.println(q.size());	//4
		//==================================================================
		//有界队列
		ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
		array.put("a");
		array.put("b");
		array.add("c");
		array.add("d");
		array.add("e");
		array.add("f");
		//System.out.println(array.offer("a", 3, TimeUnit.SECONDS));
		
		
		//阻塞队列 无界队列
		LinkedBlockingQueue<String> q2 = new LinkedBlockingQueue<String>();
		q2.offer("a");
		q2.offer("b");
		q2.offer("c");
		q2.offer("d");
		q2.offer("e");
		q2.add("f");
		//System.out.println(q.size());
		
//		for (Iterator iterator = q.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			System.out.println(string);
//		}
		
		List<String> list = new ArrayList<String>();
		System.out.println(q2.drainTo(list, 3));
		System.out.println(list.size());
		for (String string : list) {
			System.out.println(string);
		}

		//===============================================
		
		final SynchronousQueue<String> w = new SynchronousQueue<String>();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(w.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				w.add("asdasd");
			}
		});
		t2.start();		
	}
}
