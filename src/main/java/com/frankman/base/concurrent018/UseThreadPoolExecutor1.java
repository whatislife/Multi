package com.frankman.base.concurrent018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;


/**
 * 
* @ClassName: UseThreadPoolExecutor1  
* <p>Description: 线程池数据的使用   
* 
* 不同队列处理方式不同 
* (1)有界队列   
* (2)无界队列   除非系统资源耗尽，否则一直存储  第二个参数没有意义了 
* 
* 存在多种拒绝策略  4中默认策略 推荐自定义策略 
*  new DiscardOldestPolicy()
*  
*  
*  假如数据中间有操作失败了 一定是数据高峰期 将数据记录日志 数据入库
*  等后续CPU比较低的是 使用一个JOB重新推送、或者人工干预自动发送数据 
* </p>
* @date 2019年5月14日 下午9:16:47  
*
 */
public class UseThreadPoolExecutor1 {


	public static void main(String[] args) {
		/**
		 * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
		 * 若大于corePoolSize，则会将任务加入队列，
		 * 若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
		 * 若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式。
		 * 
		 */	
//		ThreadPoolExecutor poolss = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue)
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				1, 				//coreSize
				2, 				//MaxSize
				60, 			//60
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(3)			//指定一种队列 （有界队列）  有界队列的使用 
				//new LinkedBlockingQueue<Runnable>()
				, new MyRejected()
				//, new DiscardOldestPolicy()
				);
		MyTask mt1 = new MyTask(1, "任务1");
		MyTask mt2 = new MyTask(2, "任务2");
		MyTask mt3 = new MyTask(3, "任务3");
		MyTask mt4 = new MyTask(4, "任务4");
		MyTask mt5 = new MyTask(5, "任务5");
		MyTask mt6 = new MyTask(6, "任务6");
		
		pool.execute(mt1);
		pool.execute(mt2);
		pool.execute(mt3);
		pool.execute(mt4);
		pool.execute(mt5);
		pool.execute(mt6);
		
		pool.shutdown();
		
	}
}
