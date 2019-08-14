package com.frankman.base.concurrent018;
/**
 * 
* @ClassName: MyTask  
* <p>Description: 任务处理   </p>
* @date 2019年5月14日 下午9:13:26  
*
 */
public class MyTask implements Runnable {

	private int taskId;
	private String taskName;
	
	public MyTask(int taskId, String taskName){
		this.taskId = taskId;
		this.taskName = taskName;
	}
	
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public void run() {
		try {
			System.out.println("run taskId =" + this.taskId);
			Thread.sleep(5*1000);
			//System.out.println("end taskId =" + this.taskId);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	public String toString(){
		return Integer.toString(this.taskId);
	}

}
