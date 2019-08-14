package com.test;

import java.util.ArrayList;
import java.util.List;

public class TestList {
	
	public static void main(String[] args) {
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setName("1");
		user.setPwd("1");
		User user2 = new User();
		user2.setName("2");
		user2.setPwd("2");
		User user3 = new User();
		user3.setName("3");
		user3.setPwd("3");
		list.add(user);
		list.add(user2);
		list.add(user3);
	}

}
