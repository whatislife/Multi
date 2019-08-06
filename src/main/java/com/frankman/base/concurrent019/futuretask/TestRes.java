package com.frankman.base.concurrent019.futuretask;

public class TestRes {
	public static void main(String[] args) {
		
		String requestId = "requestId";
		SyncWriteFuture future = (SyncWriteFuture) SyncWriteMap.syncKey.get(requestId);
		if (future != null) {
			AsyncResponse response = new AsyncResponse();
			response.setRequestId(requestId);
			response.setReponse("你好我的名字叫frankman ");
			future.setResponse(response);
			future.setWriteResult(true);
			SyncWriteMap.syncKey.put(requestId, future);	
		}
	}
}
