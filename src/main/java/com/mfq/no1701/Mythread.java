package com.mfq.no1701;

public class Mythread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("run=" + this.isAlive());
	}

}
