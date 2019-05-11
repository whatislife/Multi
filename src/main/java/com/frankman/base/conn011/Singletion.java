package com.frankman.base.conn011;
/**
 * 
* @ClassName: Singletion  
* <p>Description: 静态内部类  </p>
* @date 2019年5月11日 下午2:30:52  
*
 */
public class Singletion {
	
	private static class InnerSingletion {
		private static Singletion single = new Singletion();
	}
	
	public static Singletion getInstance(){
		return InnerSingletion.single;
	}
	
}
