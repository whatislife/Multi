package com.test.jinke.hashMap;
/**
 * 
* @ClassName: TestMyhashMap  
* <p>Description: 
* 高级版本 
https://blog.csdn.net/ldw201510803006/article/details/80722391
  </p>
* @author  frankman
* @date 2019年6月2日 下午9:25:00  
*
 */
public class TestMyhashMap {

	public static void main(String[] args) {
         MyHashMap<String, Integer> map = new MyHashMap<>();
         map.put("Smith", 30);
         map.put("Anderson", 31);
         map.put("Lewis", 29);
         map.put("Cook", 29);
         map.put("Smith", 65);
         
         System.out.println("Entries in map: "+ map);
         
         System.out.println("The age for Lewis is " + map.get("Lewis"));
         
         System.out.println("Is Smith in the map?" + map.containKey("Smith"));
         
         System.out.println("Is age 33 in the map?" + map.containValue(33));
         
         map.remove("Smith");
         System.out.println("Entries in map: "+ map);
         
         map.clear();
         System.out.println("Entries in map: " + map);
         
	}

}

/**
Entries in map: [[Anderson, 31][Smith, 65][Lewis, 29][Cook, 29]]
The age for Lewis is 29
Is Smith in the map?true
Is age 33 in the map?false
Entries in map: [[Anderson, 31][Lewis, 29][Cook, 29]]
Entries in map: []
 */