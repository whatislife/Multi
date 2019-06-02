package com.test.jinke.hashMap.me;

import java.util.LinkedList;
/**
 * 
* @ClassName: HashMap  
* <p>Description: 自定义hashMap
* </p>
* @author  frankman
* @date 2019年6月2日 下午11:55:03  
*
 */
public class HashMap {
	private LinkedList<Node>[] table = new LinkedList[999];
	public void put(Object key,Object value){
		int hashCode = key.hashCode();
		int index = hashCode%table.length;
		if(table[index]==null){
			LinkedList list = new LinkedList();
			list.add(new Node(key,value));
			table[index]=list;
		}else{
			for(Node o : table[index]){
				if(o.getKey()==key){
					o.setValue(value);
				}
			}
		}
	}
	public Object get(Object key){
		int index = key.hashCode()%table.length;
		for(Node node : table[index]){
			if(node.getKey()==key){
				return node.getValue();
			}
		}
		return null;
	}
	private class Node{
		Object key;
		Object value;
		public Node(Object key , Object value) {
			this.key = key ;
			this.value = value;
		}
		public Object getKey() {
			return key;
		}
		public void setKey(Object key) {
			this.key = key;
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
	}
	public static void main(String[] args) {
		HashMap map = new HashMap();
		map.put("k1", "k1");
		map.put("k2", "k2");
		map.put("k2", "k3");
		System.out.println(map.get("k2"));
	}

}
