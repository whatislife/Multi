package com.test.jinke.tuzisuanfa;

import java.math.BigInteger;

/**
 * 
* @ClassName: Fibonacci  
* <p>Description: 
斐波那契数列（Fibonacci sequence），
又称黄金分割数列、因数学家列昂纳多·斐波那契（Leonardoda Fibonacci）
以兔子繁殖为例子而引入，故又称为“兔子数列”，
指的是这样一个数列：0、1、1、2、3、5、8、13、21、34、……在数学上，
斐波纳契数列以如下被以递推的方法定义：
F(0)=0，F(1)=1，F(2)=1, F(n)=F(n-1)+F(n-2)（n>=3，n∈N*）
用java语言实现一个算法 斐波那契数列 并输出前10000个数的值 
分析
f(0)=0
f(1)=1
f(2)=f(1)+f(0)=1+0=1   1*f(1)+0
f(3)=f(2)+f(1)=f(1)+f(1)+f(0)=2  2*f(1)+0
f(4)=f(3)+f(2)=f
https://blog.csdn.net/readerjun0314/article/details/51657277
 </p>
* @author  frankman
* @date 2019年5月31日 下午5:16:01  
*
 */
public class Fibonacci {
	/**
	 * 
	* <p>Title: fibonacci  </p>
	* Description: 递归方式 效率低 
	* @author  frankman
	* @date 2019年6月1日 下午3:23:41  
	* @param n
	* @return
	 */
	public static int fibonacci(int n){
		if(n==0){
			return 0;
		}else if(n <= 2){
			return 1;
		}else{
			return fibonacci(n-1)+fibonacci(n-2);
		}
	}
	public static int f(int n ){
		if(n==0){
			return 0;
		}else if(n<=2){
			return 1;
		}else{
			return f(n-1)+f(n-2);
		}
	}
	/**
	 * 
	* <p>Title: fibonacciNormal  </p>
	* Description: 地推算法效率高
	* @author  frankman
	* @date 2019年6月1日 下午3:24:22  
	* @param n
	* @return
	 */
	public static BigInteger fibonacciNormal(int n){
		if (n == 1) {
		return new BigInteger("0");
		}
		BigInteger n1 = new BigInteger("0"), n2 = new BigInteger("1"), sn = new BigInteger("0");
		for (int i = 0; i < n - 1; i++) {
		sn = n1.add(n2);
		n1 = n2;
		n2 = sn;
		}
		return sn;
		} 
	public static BigInteger ff(int n){
		if(n ==1){
			return new BigInteger("1");
		}
		BigInteger n1 = new BigInteger("0"),n2 = new BigInteger("1"),sn = new BigInteger("0");
		for(int i = 1 ; i< n ; i++){
			sn = n1.add(n2);
			n1=n2;
			n2=sn;
		}
		return sn;
	}
	public static void main(String[] args) {
		System.out.println(Fibonacci.fibonacciNormal(50));
		System.out.println(Fibonacci.ff(50));
	}

}
