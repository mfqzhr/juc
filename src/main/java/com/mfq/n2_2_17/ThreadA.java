package com.mfq.n2_2_17;

public class ThreadA extends Thread{
	private Service service;
	public ThreadA(Service service) {
		// TODO Auto-generated constructor stub
		this.service = service;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		service.print("AA");
	}
}
