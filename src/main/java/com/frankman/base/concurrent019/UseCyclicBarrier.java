package com.frankman.base.concurrent019;
import java.io.IOException;  
import java.util.Random;  
import java.util.concurrent.BrokenBarrierException;  
import java.util.concurrent.CyclicBarrier;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors; 
/**
 * 
* @ClassName: UseCyclicBarrier  
* <p>Description: 
https://www.baidu.com/link?url=PQdyYhnTgtqDOo4YAMu_jNaevSztjxUAf-BW3c9jMdiZ-3m_hdxuM9mkH_pWGFNjUx242MnV6XMpIaadc5Mrja&wd=&eqid=e9c6fc7600148012000000025cde7461
 等待一起执行  
 
 
 多个运动员操作
一组满足调用await 三个线程同时执行代码 准备ok了 
  </p>
* @date 2019年5月17日 下午4:47:09  
*
 */
public class UseCyclicBarrier {

	static class Runner implements Runnable {  
	    private CyclicBarrier barrier;  
	    private String name;  
	    
	    public Runner(CyclicBarrier barrier, String name) {  
	        this.barrier = barrier;  
	        this.name = name;  
	    }  
	    @Override  
	    public void run() {  
	        try {  
	            Thread.sleep(1000 * (new Random()).nextInt(5));  
	            System.out.println(name + " 准备OK.");  
	            barrier.await();  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        } catch (BrokenBarrierException e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println(name + " Go!!");  
	    }  
	} 
	
    public static void main(String[] args) throws IOException, InterruptedException {  
        CyclicBarrier barrier = new CyclicBarrier(3);  // 3 
        ExecutorService executor = Executors.newFixedThreadPool(3);  
        
        executor.submit(new Thread(new Runner(barrier, "zhangsan")));  
        executor.submit(new Thread(new Runner(barrier, "lisi")));  
        executor.submit(new Thread(new Runner(barrier, "wangwu")));  
  
        executor.shutdown();  
    }  
  
}  