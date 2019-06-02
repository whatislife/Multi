package com.test.jinke.link;

public class TestSingleLinkedList{
  public static void main(String[] args) {
	  SingleLinkedList singleList = new SingleLinkedList();
	    singleList.addHead("A");
	    singleList.addHead("B");
	    singleList.addHead("C");
	    singleList.addHead("D");
	    //singleList.addHead("A");
	    
	    singleList.addLast("W");
	    //打印当前链表信息
	    singleList.display();
	    //删除C
	    //singleList.delete("A");
	    Boolean flag = true;
	    while(flag){
	    	flag = singleList.delete("A");
	    }
	    singleList.display();
	    //查找B
	   // System.out.println(singleList.find("B"));
}
}
