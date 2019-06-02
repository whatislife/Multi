package com.test.jinke.link;

public class LinkList {
	
	private int size ;
	private Node head;
	
	private class Node{
		Object obj;
		Node next;
		public Node(Object obj) {
			this.obj = obj;
		}
	}
	public void addHead(Object obj){
		Node nodeNew = new Node(obj);
		if(size == 0){
			head =nodeNew;
		}else{
			nodeNew.next = head;
			head = nodeNew;
			
		}
		size++;
	}
	public void addLast(Object obj){
		Node nodeNew = new Node(obj);
		Node nodeLast = getlastNode();
		if(size ==0){
			head = nodeNew;
		}else{
			nodeLast.next = nodeNew;
		}
		size++;
		
	}
	public Node getlastNode(){
		Node lastNode = head;
		if(size == 0 ){
			return null;
		}else{
			while(lastNode.next!=null){
				lastNode = lastNode.next;
			}
		}
		return lastNode;
	}
	public Boolean delete(Object obj){
		if(size ==0){
			return false;
		}
		Node currentNode = head;
		Node preNode = head;
		//1.找到这个节点 
		//2.删除
		while(currentNode.obj != obj){
			if(currentNode.next==null){//最后一个节点了
				return false;
			}
		    preNode = currentNode;
		    currentNode = currentNode.next;
		}
		System.out.println("当前节点-"+currentNode.obj);
		if(currentNode == head){
			//如果是头结点 
			head = currentNode.next;
			size--;
		}else{
			//删除本节点
			preNode.next= currentNode.next;
			size--;
		}
		return true;	
		
	}
	public void disPlay(){
		if(size==0){
			System.out.println("链表数据是null");
		}else{
			Node nowNode = head;
			StringBuffer buf = new StringBuffer();
			while(nowNode!=null){
				buf.append(">"+nowNode.obj);
				nowNode = nowNode.next;
			}
			System.out.println("链表数据是"+buf.toString());
		}
		
	} 
	
	public static void main(String[] args) {
		LinkList list = new LinkList();
		list.addHead("A");
		list.addHead("B");
		list.addLast("C");
		list.addLast("D");
		list.disPlay();
		list.delete("B");
		list.disPlay();
	}
}
