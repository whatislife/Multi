package com.frankman.base.coll013;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 
* @ClassName: UseConcurrentMap  
* <p>Description:
1.原始map怎么使用方式相同
2. 
  </p>
* @date 2019年5月11日 下午4:12:05  
*
 */
public class UseConcurrentMap {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String, Object>();
		chm.put("k1", "v1");
		chm.put("k2", "v2");
		chm.put("k3", "v3");
		chm.putIfAbsent("k4", "vvvv");
		//System.out.println(chm.get("k2"));
		//System.out.println(chm.size());
		
		for(Map.Entry<String, Object> me : chm.entrySet()){
			System.out.println("key:" + me.getKey() + ",value:" + me.getValue());
		}
		
		
		
	}
}
