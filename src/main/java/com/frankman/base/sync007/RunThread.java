package com.frankman.base.sync007;
/**
 * 
* @ClassName: RunThread  
* <p>Description: volatile关键字
* 变量在多个线程间可见  不具备原子性 
* </p>
* @date 2019年5月8日 下午9:22:22  
*
 */
public class RunThread extends Thread{
    //volatile
	private  boolean isRunning = true;
	
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public void run(){
		System.out.println("进入run方法..");
		while(isRunning == true){//空的数据轮询 知道 false停止线程了 
			//System.out.println("============");
		}
		System.out.println("线程停止");
	}
	
	public static void main(String[] args) throws InterruptedException {
	
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(3000);        
		rt.setRunning(false);
		System.out.println("isRunning的值已经被设置了false");
		//Thread.sleep(1000);
		//System.out.println(rt.isRunning);

	}
	
	
}
