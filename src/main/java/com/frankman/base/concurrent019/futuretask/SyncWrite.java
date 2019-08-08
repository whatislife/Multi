package com.frankman.base.concurrent019.futuretask;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SyncWrite {

	public static WriteFuture<AsyncResponse> writeAndSync(final Object channel, AsyncRequest request) throws Exception {

		if (channel == null) {
			throw new NullPointerException("channel");
		}
		if (request == null) {
			throw new NullPointerException("request");
		}

		WriteFuture<AsyncResponse> future = SyncWriteMap.syncKey.get(request.getRequestId());
		if (future != null){
			return future;
		}
		future = new SyncWriteFuture(request.getRequestId());
		SyncWriteMap.syncKey.put(request.getRequestId(), future);
		System.out.println("request cache size:{}"+SyncWriteMap.syncKey.size());
		doWriteAndSync(channel, request, future);
		//System.out.println("remove request {}" + request.getRequestId());
		//SyncWriteMap.syncKey.remove(request.getRequestId());
		return future;
	}

	private static void doWriteAndSync(final Object channel, AsyncRequest request,WriteFuture<AsyncResponse> writeFuture) throws Exception {

		try {
			//channel.writeAndFlush(request.getCommand().getMessage());
			//log.info("success async send request to park: {}", request.getCommand().getMessage());
			//业务处理 tcp 推送 
			System.out.println("channel=========数据:"+channel);
		} catch (Exception e) {
			//SyncWriteMap.syncKey.remove(writeFuture.requestId());
			throw e;
		}

	}

}
