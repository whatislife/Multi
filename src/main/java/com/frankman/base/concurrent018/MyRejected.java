package com.frankman.base.concurrent018;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
/**
 * 
* @ClassName: MyRejected  
* <p>Description: 拒绝的提示 RejectedExecutionHandler 
* 默认有四种，不是特别合理 
* 希望自动以策略使用 
* </p>
* @date 2019年5月14日 下午9:12:54  
*
 */
public class MyRejected implements RejectedExecutionHandler{

	
	public MyRejected(){
	}
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("自定义处理..");
		System.out.println("当前被拒绝任务为：" + r.toString());
		//显示被拒绝的任务  
	}

	
	
}
