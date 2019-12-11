package com.frankman.base.Multi;

import java.util.ArrayList;
import java.util.List;

public class Testm {
	
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 100 ; i<=999;i++){
			int b100 =i/100;
	        int b10  =i/10%10;
	        int b1   =i%10;
	        int sum = b100+b10+b1;
	        //System.out.println(i+"-"+sum);
	        //System.out.println(i/sum);
	        if(i%sum==0){
	        	//System.out.println("数字："+i+" 三位和："+sum);
	        	list.add(i);
	        }
		}
		int num = 0 ;
		for(int j = 0 ; j <= list.size() ; j ++){
			if(j<list.size()-1){
				System.out.println(list.get(j)+"-"+list.get(j+1)+"差值："+(list.get(j+1)-list.get(j)));
				if((list.get(j+1)-list.get(j))>=num){
					num = (list.get(j+1)-list.get(j));
				}
			}
		}
		System.out.println(num);
	}

}
