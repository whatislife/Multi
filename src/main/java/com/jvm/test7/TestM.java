package com.jvm.test7;
/**
 * 
* @ClassName: TestM  
* <p>Description: 垃圾回收  </p>
* @author  frankman
* @date 2020年3月3日 下午3:32:04  
* 
*参数： -verbose:gc -XX:+PrintGCDetails
*
*结果：
Heap
 PSYoungGen      total 306176K, used 10506K [0x00000000eaa80000, 0x0000000100000000, 0x0000000100000000)
  eden space 262656K, 4% used [0x00000000eaa80000,0x00000000eb4c29b0,0x00000000fab00000)
  from space 43520K, 0% used [0x00000000fd580000,0x00000000fd580000,0x0000000100000000)
  to   space 43520K, 0% used [0x00000000fab00000,0x00000000fab00000,0x00000000fd580000)
 ParOldGen       total 699392K, used 0K [0x00000000bff80000, 0x00000000eaa80000, 0x00000000eaa80000)
  object space 699392K, 0% used [0x00000000bff80000,0x00000000bff80000,0x00000000eaa80000)
 PSPermGen       total 21504K, used 2570K [0x00000000bad80000, 0x00000000bc280000, 0x00000000bff80000)
  object space 21504K, 11% used [0x00000000bad80000,0x00000000bb002988,0x00000000bc280000)

* 证明 未使用引用计数法 否则不会进行垃圾回收  
*
 */
public class TestM {
	private Object instance;
	public TestM() {
		byte[] b = new byte[20 * 1024 * 1024];
	}
	public static void main(String[] args) {

		TestM m1 = new TestM();
		TestM m2 = new TestM();
		m1.instance = m2 ; 
		m2.instance = m1;
		m1 = null;
		m2 = null ; 
		System.gc();
		//垃圾回收期使用 paralle
	}

}
