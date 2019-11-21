package com.mfq.n2_2_2;

public class MyThread1 extends Thread {
	private Task task;
	public MyThread1(Task task) {
		// TODO Auto-generated constructor stub
		this.task = task;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		task.doLongTimeTask();
	}

}
