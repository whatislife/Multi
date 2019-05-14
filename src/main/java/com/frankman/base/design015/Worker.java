package com.frankman.base.design015;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * 
* @ClassName: Worker  
* <p>Description: 每个查分的任务 线程类  </p>
* @date 2019年5月14日 下午1:43:39  
*
 */
public class Worker implements Runnable {
    //master的引用 队列的引用
	private ConcurrentLinkedQueue<Task> workQueue;
	//结果的引用 
	private ConcurrentHashMap<String, Object> resultMap;
	
	public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	@Override
	public void run() {
		while(true){
			//从队列中获取数据
			Task input = this.workQueue.poll();
			if(input == null) break;//如果为空了就处理完成了 返回
			Object output = handle(input);//操作获取结果 ，可以操作数据等 
			//将结果返回到结果map中 
			this.resultMap.put(Integer.toString(input.getId()), output);
		}
	}
    /**
     * 
    * <p>Title: handle  </p>
    * Description: 这个方法可以是 返回空的， 集成这个类的类，重写了这个方法，自定义handle方法内容 解耦了 
    * @param input
    * @return
     */
	private Object handle(Task input) {
		Object output = null;
		try {
			//处理任务的耗时。。 比如说进行操作数据库。。。
			Thread.sleep(500);
			output = input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;
	}



}
