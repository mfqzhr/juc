package com.mfq.n2_2_1;

public class MyThread2 extends Thread {
	private Task task;
	public MyThread2(Task task) {
		// TODO Auto-generated constructor stub
		this.task = task;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		CommonUtils.beginTime2 = System.currentTimeMillis();
		task.doLongTimeTask();
		CommonUtils.endTime2 = System.currentTimeMillis();
	}

}
