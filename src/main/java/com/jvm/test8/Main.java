package com.jvm.test8;
/**
 * 
* @ClassName: Main  
* <p>Description: 内存Eden优先分配   </p>
* @author  frankman
* @date 2020年3月3日 下午9:24:29  
* 
* -verbose:gc -XX:+PrintGCDetails -XX:+UseSerialGC
* https://blog.csdn.net/shi2huang/article/details/80085193
*可以通过参数判断垃圾回收器使用的哪一种 
*Heap
 PSYoungGen      total 306176K, used 15759K [0x00000000eaa80000, 0x0000000100000000, 0x0000000100000000)
  eden space 262656K, 6% used [0x00000000eaa80000,0x00000000eb9e3ec8,0x00000000fab00000)
  from space 43520K, 0% used [0x00000000fd580000,0x00000000fd580000,0x0000000100000000)
  to   space 43520K, 0% used [0x00000000fab00000,0x00000000fab00000,0x00000000fd580000)
 ParOldGen       total 699392K, used 0K [0x00000000bff80000, 0x00000000eaa80000, 0x00000000eaa80000)
  object space 699392K, 0% used [0x00000000bff80000,0x00000000bff80000,0x00000000eaa80000)
 PSPermGen       total 21504K, used 2570K [0x00000000bad80000, 0x00000000bc280000, 0x00000000bff80000)
  object space 21504K, 11% used [0x00000000bad80000,0x00000000bb0028e8,0x00000000bc280000)

 */
public class Main {
	public static void main(String[] args) {
		
		byte[] b = new byte[ 4 * 1024 * 1024];
		
		
	}
	

}
