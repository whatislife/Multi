package com.frankman.base.concurrent019.futuretask;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSON;

public class TestReq {
	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				start();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				end();
			}
		},"t2");
		t1.start();
		Thread.sleep(1000);
		t2.start();
	}
	private static void end(){
		String requestId = "requestId";
		SyncWriteFuture future = (SyncWriteFuture) SyncWriteMap.syncKey.get(requestId);
		if (future != null) {
			AsyncResponse response = new AsyncResponse();
			response.setRequestId(requestId);
			response.setReponse("你好我的名字叫frankman333 ");
//			future.setResponse(null);//设置超时判断
			future.setResponse(response);//不设置超时判断
			future.setWriteResult(true);
		//	SyncWriteMap.syncKey.put(requestId, future);	
		}
	}
	
	private static void start(){
		//发送数据
		MQMessage mq  = new MQMessage();
		mq.setParkID("parkId");
		mq.setBuszKey("busyzKey");
		mq.setOperNum(UUID.randomUUID().toString());
		mq.setTarget("target");
		mq.setSource("source");
		mq.setMessage("发送消息");
		mq.setForce(true);
		AsyncRequest request = new AsyncRequest();
		request.setRequestId("requestId");
		request.setCommand(mq);
		
		//数据推送
		WriteFuture<AsyncResponse> response = null;
		try {
			response = SyncWrite.writeAndSync("channel-data-frankman",request);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//等待数据结果
		AsyncResponse asyncResponse = null;
		try {
			asyncResponse = response.get(6, TimeUnit.SECONDS);
			if(asyncResponse==null){
				if (response.isTimeout()) {
					SyncWriteMap.syncKey.remove(response.requestId());
					throw new TimeoutException();
				} else { 
					SyncWriteMap.syncKey.remove(response.requestId());
					throw new Exception(response.cause());
				}
			}
			SyncWriteMap.syncKey.remove(response.requestId());
		} catch (Exception e) {
			SyncWriteMap.syncKey.remove(response.requestId());
			e.printStackTrace();
		}
		System.out.println("JSON数据是："+JSON.toJSON(asyncResponse));
	}
	private static void start2(){
		//发送数据
		MQMessage mq  = new MQMessage();
		mq.setParkID("parkId");
		mq.setBuszKey("busyzKey");
		mq.setOperNum(UUID.randomUUID().toString());
		mq.setTarget("target");
		mq.setSource("source");
		mq.setMessage("发送消息");
		mq.setForce(true);
		AsyncRequest request = new AsyncRequest();
		request.setRequestId("requestId");
		request.setCommand(mq);
		
		//数据推送 进入MQ
		WriteFuture<AsyncResponse> response = null;
		try {
			response = SyncWrite.writeAndSync("channel-data-frankman",request);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//等待数据结果  
		AsyncResponse asyncResponse = null;
		try {
			asyncResponse = response.get(6, TimeUnit.SECONDS);
			if(asyncResponse==null){
				if (response.isTimeout()) {
					SyncWriteMap.syncKey.remove(response.requestId());
					throw new TimeoutException();
				} else { 
					SyncWriteMap.syncKey.remove(response.requestId());
					throw new Exception(response.cause());
				}
			}
			SyncWriteMap.syncKey.remove(response.requestId());
		} catch (Exception e) {
			SyncWriteMap.syncKey.remove(response.requestId());
			e.printStackTrace();
		}
		System.out.println("JSON数据是："+JSON.toJSON(asyncResponse));
		
		//本地MQ监听数据
		
	}
	

}
