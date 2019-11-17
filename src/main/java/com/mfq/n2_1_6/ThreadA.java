package com.mfq.n2_1_6;

public class ThreadA extends Thread{
	private PublicVar publicVar;
	public ThreadA(PublicVar publicVar) {
		this.publicVar = publicVar;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		publicVar.setValue("B", "BB");
		
	}

}
