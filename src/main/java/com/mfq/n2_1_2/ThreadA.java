package com.mfq.n2_1_2;

public class ThreadA extends Thread {
	private HasSelfPrivateNum hasSelfPrivateNum;

	public ThreadA(HasSelfPrivateNum hasSelfPrivateNum) {
		super();
		this.hasSelfPrivateNum = hasSelfPrivateNum;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		hasSelfPrivateNum.addI("a");
	}

}
