package com.frankman.base.sync001;

/**
 * 
* @ClassName: MyThread  
* <p>Description:  
*  线程安全概念：当多个线程访问某一个类（对象或方法）时，这个对象始终都能表现出正确的行为，那么这个类（对象或方法）就是线程安全的。
* synchronized：可以在任意对象及方法上加锁，而加锁的这段代码称为"互斥区"或"临界区" 
* 
*  
* </p>
* @date 2019年4月22日 下午1:38:36  
*
 */
public class MyThread extends Thread{
	
	private int count = 5 ;
	
	//synchronized加锁   添加在方法是对象锁，添加static 是类锁 
	public  synchronized void  run(){
		count--;
		System.out.println(this.currentThread().getName() + " count = "+ count);
	}

	public static void main(String[] args) {
		/**
		 * 分析：当多个线程访问myThread的run方法时，以排队的方式进行处理（这里排对是按照CPU分配的先后顺序而定的），
		 * 		一个线程想要执行synchronized修饰的方法里的代码：
		 * 1.尝试获得索
		 * 2.如果拿到锁 执行synchronized代码中内容
		 * 如果拿不到锁 这个线程不断尝试获取这把锁，直到拿到为止 多个线程去竞争这把锁 （也就会存在锁竞争问题）
		 */
		MyThread myThread = new MyThread();
		Thread t1 = new Thread(myThread,"t1");
		Thread t2 = new Thread(myThread,"t2");
		Thread t3 = new Thread(myThread,"t3");
		Thread t4 = new Thread(myThread,"t4");
		Thread t5 = new Thread(myThread,"t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
	}
}












