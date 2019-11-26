package com.mfq.n2_3_1;

public class ThreadA extends Thread{
	private Service service;
	public ThreadA() {
		// TODO Auto-generated constructor stub
		this.service = service;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		service.runMethod();
	}

}
