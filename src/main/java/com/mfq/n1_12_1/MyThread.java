package com.mfq.n1_12_1;

public class MyThread extends Thread {
	private long i = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (true) {
			i++;
		}

	}

	public long getI() {
		return i;
	}

	public void setI(long i) {
		this.i = i;
	}
}
