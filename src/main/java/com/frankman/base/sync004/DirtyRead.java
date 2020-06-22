package com.frankman.base.sync004;
/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性。
 * 主要线程数
 * 1.主线程 睡眠1s ， 之后执行getValue
 * 2.t1线程 执行run方法，调用setvalue需要时间
 *
 */
public class DirtyRead {

	private String username = "bjsxt";
	private String password = "123";
	
	public synchronized void setValue(String username, String password){
		this.username = username;
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.password = password;
		
		System.out.println("setValue最终结果：username = " + username + " , password = " + password);
	}
	//都需要添加synchronized getValue需要等待setvalue结果
	public  void getValue(){
		System.out.println("getValue方法得到：username = " + this.username + " , password = " + this.password);
	}
	
	
	public static void main(String[] args) throws Exception{
		
		final DirtyRead dr = new DirtyRead();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				dr.setValue("z3", "456");		
			}
		});
		t1.start();
		Thread.sleep(1000);
		
		dr.getValue();
	}
	
	
	
}
