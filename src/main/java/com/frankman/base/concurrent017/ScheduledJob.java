package com.frankman.base.concurrent017;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/**
 * 
* @ClassName: Temp  
* <p>Description: 定时执行这个线程  </p>
* @date 2019年5月14日 下午9:11:03 
*  
 */
class Temp extends Thread {
    public void run() {
        System.out.println("run"+Thread.currentThread().getName() );
    }
}

public class ScheduledJob {
	
    public static void main(String args[]) throws Exception {
    
    	Temp command = new Temp();
    	
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(command, 5, 1, TimeUnit.SECONDS);
    
    }
}