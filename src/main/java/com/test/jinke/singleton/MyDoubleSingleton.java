package com.test.jinke.singleton;
/**
 * 
* @ClassName: MyDoubleSingleton  
* <p>Description: 双锁检测   </p>
* @author  frankman
* @date 2019年6月1日 下午3:32:58  
*
 */
public class MyDoubleSingleton {
	//1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
	//2）禁止进行指令重排序。
	private static volatile MyDoubleSingleton singleton;
	public static MyDoubleSingleton getIntance(){
		if(singleton == null){
			synchronized(MyDoubleSingleton.class){
				if(singleton == null){
					singleton = new MyDoubleSingleton();
				}
			}
		}
		return singleton;
	}
	

}
