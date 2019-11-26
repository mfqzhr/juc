package com.mfq.n2_3_1;

public class ThreadB extends Thread{
	private Service service;
	public ThreadB() {
		// TODO Auto-generated constructor stub
		this.service = service;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		service.stopMethod();
	}

}
