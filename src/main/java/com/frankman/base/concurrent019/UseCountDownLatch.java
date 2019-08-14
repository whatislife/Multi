package com.frankman.base.concurrent019;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/**
 * 
* @ClassName: UseCountDownLatch  
* <p>Description: 
await 开始等待
等后续 countDown.countDown(); 次数达到 上边线程才会执行 
</p>
* @date 2019年5月17日 下午4:43:18  
*
 */
public class UseCountDownLatch {

	public static void main(String[] args) {
		
		final CountDownLatch countDown = new CountDownLatch(1);
		
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("进入线程t1" + "等待其他线程处理完成...");
					countDown.await();
					//countDown.await(2,TimeUnit.SECONDS);
					System.out.println("t1线程继续执行...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t2线程进行初始化操作...");
					Thread.sleep(6000);
					System.out.println("t2线程初始化完毕，通知t1线程继续...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t3线程进行初始化操作...");
					Thread.sleep(4000);
					System.out.println("t3线程初始化完毕，通知t1线程继续...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		//t3.start();
		
		Thread t22 = new Thread(new Runnable(){
			@Override 
			public void run(){
				try {
					countDown.countDown();
					countDown.await();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
		});
		
		
		
		
	}
}
