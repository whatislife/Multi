package com.test.jinke.hashMap.me;

import java.util.LinkedList;
/**
 * 
* @ClassName: FinalMap  
* <p>Description: 
* 简单实现原理正常 
* https://www.cnblogs.com/cxy2016/p/7144264.html 
* 格外的记录 
* https://www.cnblogs.com/yuanblog/p/4441017.html
*  </p>
* @author  frankman
* @date 2019年6月2日 下午11:33:56  
*
 */
public class FinalMap {
	
	public static void main(String[] args) {
		FinalMap map = new FinalMap();
		map.put("k1", "k1");
		map.put("k2", "k2");
		map.put("k2", "k22");
		Object value = map.get("k2");
		System.out.println(value);
	}
	
//    private int size;
    private LinkedList[] map = new LinkedList[999];//链表数组
    public FinalMap(){
        
    }
    /*
     * 添加的方法
     * */
    public boolean put(Object key, Object value){
        int hashCode = key.hashCode();
        System.out.println("hashCode:"+hashCode);
        int index = hashCode % map.length;
        System.out.println("index:"+index);
        if(map[index] == null){//如果初试值为null，就添加一个新的链表。
            LinkedList ll = new LinkedList();
            ll.add(new Node(key, value));
            map[index] = ll;
        }else{//如果不为空，则先判断键是否重复。若是，则替换值；若否，则直接添加信息。
            for(Object o : map[index])
                if(((Node)o).getKey().equals(key))
                    ((Node)o).setValue(value);
                else
                	System.out.println();
                    //map[index].add(new Object());
        }
        return true;
    }
    /*
     * 取值的方法
     * */
    public Object get(Object key){
        int hashCode = key.hashCode();
        int index = hashCode % map.length;
        for(Object o : map[index])
            if(((Node)o).getKey().equals(key))
                return ((Node)o).getValue();
        return null;
    }
}
/*
 * 存储的基本单元类
 * */
class Node{
    Object key;
    Object value;
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
    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
    
}