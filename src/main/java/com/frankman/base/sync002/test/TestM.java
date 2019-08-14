package com.frankman.base.sync002.test;


public class TestM {
	//注意观察run方法输出顺序
		public static void main(String[] args) {
			
			//俩个不同的对象
			final SnowFlakeStrategy m1 = new SnowFlakeStrategy();
			final SnowFlakeStrategy m2 = new SnowFlakeStrategy();
			final SnowFlakeStrategy m3 = new SnowFlakeStrategy();
			
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					m1.getPrimaryId();
				}
			});
			
			Thread t2 = new Thread(new Runnable() {
				@Override 
				public void run() {
					m2.getPrimaryId();
				}
			});		
			Thread t3 = new Thread(new Runnable() {
				@Override 
				public void run() {
					m3.getPrimaryId2();
				}
			});
			
			t1.start();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t2.start();
			t3.start();
			
		}

}
