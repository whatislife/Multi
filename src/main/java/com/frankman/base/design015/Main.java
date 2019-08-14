package com.frankman.base.design015;

import java.util.Random;
/**
 * 
* @ClassName: Main  
* <p>Description: 之后可以引入线程池操作   </p>
* @date 2019年5月14日 下午1:50:03  
*
 */
public class Main {

	public static void main(String[] args) {
		// 一个任务分成20个job处理
		Master master = new Master(new Worker(), 20);
		String name = "";
		master.start(name);
		//生产数据进入队列 
		Random r = new Random();
		for(int i = 1; i <= 100; i++){
			Task t = new Task();
			t.setId(i);
			t.setPrice(r.nextInt(1000));
			master.submit(t);
		}
		master.execute();
		long start = System.currentTimeMillis();
		
		while(true){
			if(master.isComplete()){
				long end = System.currentTimeMillis() - start;
				int priceResult = master.getResult();
				System.out.println("最终结果：" + priceResult + ", 执行时间：" + end);
				break;
			}
		}
		
	}
}
