package com.frankman.base.concurrent019.futuretask;


public class AsyncRequest {
	
    private String requestId;
    private MQMessage command;
    
    public AsyncRequest() {
		super();
	}

	public AsyncRequest(String requestId, MQMessage command) {
		super();
		this.requestId = requestId;
		this.command = command;
	}

	public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

	public MQMessage getCommand() {
		return command;
	}

	public void setCommand(MQMessage command) {
		this.command = command;
	}

    

}
