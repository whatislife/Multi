package com.frankman.base.concurrent017;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
* @ClassName: UseExecutors  
* <p>Description: 查看线程池的源码，
* 本质是 ThreadPoolExecutor定时、唯一、固定、无限个
* </p>
* @date 2019年5月14日 下午9:02:10  
*
 */
public class UseExecutors {

	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newSingleThreadExecutor();
		Executors.newCachedThreadPool();
		Executors.newFixedThreadPool(10);
		Executors.newScheduledThreadPool(10);
		//cache fixed single
	}
}
