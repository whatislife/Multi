package com.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
* @ClassName: TestM  
https://blog.csdn.net/sysmedia/article/details/78030113 缓冲读取 
* https://blog.csdn.net/clevercode/article/details/81743736 分而治之  大数据量统计排序 
* 
* @date 2019年5月23日 下午6:02:13  
*
 */
public class TestM {


	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]{1}");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]{1}");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;

	}

	public static String sumScore() throws Exception {
		StringBuilder builder = new StringBuilder();
		StringBuffer buffer = new StringBuffer();
		/*
		 * String fileName = "D:\\file.txt"; FileReader file =new
		 * FileReader(fileName); reader = new BufferedReader(file);
		 */

		FileInputStream fis = new FileInputStream("D:\\file.txt");
		InputStreamReader isr = new InputStreamReader(fis, "GBK");
		BufferedReader br = new BufferedReader(isr);
		
//		File file = new File("D:\\file.txt");   
//		BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));    
//		BufferedReader reader = new BufferedReader(new InputStreamReader(fis,"GBK"),5*1024*1024);// 用5M的缓冲读
//		
		String s = "";
		while ((s = br.readLine()) != null) {
			buffer.append(s);
		}
		return buffer.toString();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(sumScore());
		/*
		 * String num = "0rrrrr"; Boolean isNum = isNumeric(num);
		 * System.out.println(isNum);
		 */

	}
}
