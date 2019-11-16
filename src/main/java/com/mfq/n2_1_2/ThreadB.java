package com.mfq.n2_1_2;

public class ThreadB extends Thread {
	private HasSelfPrivateNum hasSelfPrivateNum;

	public ThreadB(HasSelfPrivateNum hasSelfPrivateNum) {
		super();
		this.hasSelfPrivateNum = hasSelfPrivateNum;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		hasSelfPrivateNum.addI("b");
	}

}
