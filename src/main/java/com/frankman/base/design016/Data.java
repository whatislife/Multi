package com.frankman.base.design016;
/**
 * 
* @ClassName: Data  
* <p>Description: 传递的数据  </p>
* @date 2019年5月14日 下午1:51:02  
*
 */
public final class Data {

	private String id;
	private String name;
	
	public Data(String id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString(){
		return "{id: " + id + ", name: " + name + "}";
	}
	
}
