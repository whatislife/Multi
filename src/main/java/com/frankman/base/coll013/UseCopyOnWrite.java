package com.frankman.base.coll013;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 
* @ClassName: UseCopyOnWrite  
* <p>Description: 
1.读多写少的场景下 操作比较合理 
2.list set使用方式的 
3.底层都是加锁的
4.每一个写都会复制一份原始容器数据 到一个新的容器数据，操作完新的容器之后，将原始指针只想新的容器 原始容器数据垃圾回收了 
5.使用冲入锁机制，保证数据的一致性 


  </p>
* @date 2019年5月11日 下午4:06:16  
*
 */
public class UseCopyOnWrite {

	public static void main(String[] args) {
		
		CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<String>();
		cwal.add("");
		/*源码 复制 
		 * public boolean add(E e) {
	        final ReentrantLock lock = this.lock;
	        lock.lock();
	        try {
	            Object[] elements = getArray();
	            int len = elements.length;
	            Object[] newElements = Arrays.copyOf(elements, len + 1);
	            newElements[len] = e;
	            setArray(newElements);
	            return true;
	        } finally {
	            lock.unlock();
	        }
	    }*/
		CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<String>();
		
		
	}
}
