package com.test;

import java.util.List;

public class User {
	private String name ; 
	private String pwd2;
	private String pwd ; 
	List<User> list ; 
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
