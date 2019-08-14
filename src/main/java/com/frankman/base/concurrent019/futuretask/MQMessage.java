package com.frankman.base.concurrent019.futuretask;


import java.io.Serializable;

public class MQMessage implements Serializable {
	
	private static final long serialVersionUID = -4010104872438848358L;
	private String parkID;
	private String buszKey;
	private String operNum;
	private String target;
	private String source;
	private String message;
	private boolean force;
	
	public String getParkID() {
		return parkID;
	}
	public void setParkID(String parkID) {
		this.parkID = parkID;
	}
	
	public String getBuszKey() {
		return buszKey;
	}
	public void setBuszKey(String buszKey) {
		this.buszKey = buszKey;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isForce() {
		return force;
	}
	public void setForce(boolean force) {
		this.force = force;
	}
	public String getOperNum() {
		return operNum;
	}
	public void setOperNum(String operNum) {
		this.operNum = operNum;
	}
	
	
	
	
}
