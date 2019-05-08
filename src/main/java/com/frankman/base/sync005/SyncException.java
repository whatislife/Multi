package com.frankman.base.sync005;
/**
 * synchronized异常  可以20个任务全部停止，也可以继续执行添加日志记录 
 *
 */
public class SyncException {

	private int i = 0;
	public synchronized void operation(){
		while(true){
			try {
				i++;
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + " , i = " + i);
				if(i == 20){
					//Integer.parseInt("a");
					throw new RuntimeException();
				}
			} catch (Exception e) {//InterruptedException
				e.printStackTrace();
				//System.out.println("记录日志"+i);
				//throw new RuntimeException();//抛出运行时异常 整体打断 或者 intercept
				//continue;
			}
		}
	}
	
	public static void main(String[] args) {
		
		final SyncException se = new SyncException();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				se.operation();
			}
		},"t1");
		t1.start();
	}
	
	
}
