package com.frankman.base.concurrent017;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/**
 * 
* @ClassName: Temp  
* <p>Description: 定时执行这个线程  
* 
* 线程定时任务 其实就是放进队列中，结合监管平台定时执行问题
* </p>
* @date 2019年5月14日 下午9:11:03 
*  
 */
class Temp extends Thread {
	public Temp() {
	}
    public void run() {
    	Thread.currentThread().setName("BlueBrainInTaskThread"+new Date());
    	//while(true){
    		try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		System.out.println("run"+Thread.currentThread().getName()+"======="+new Date());
    	//}
    }
}

public class ScheduledJob {
	
    public static void main(String args[]) throws Exception {
    
    	Temp command = new Temp();
    	
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        try {
        	ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(command, 5, 2, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    
    }
}