package com.frankman.base.concurrent019.futuretask;


public class AsyncResponse {

    private String requestId;
    private Object reponse;    
    
    public AsyncResponse() {
		super();
	}

	public AsyncResponse(String requestId, String reponse) {
		super();
		this.requestId = requestId;
		this.reponse = reponse;
	}

	public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

	public Object getReponse() {
		return reponse;
	}

	public void setReponse(Object reponse) {
		this.reponse = reponse;
	}
    
    
}
