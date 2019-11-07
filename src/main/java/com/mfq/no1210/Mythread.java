package com.mfq.no1210;

public class Mythread extends Thread{
	private int i = 5;
	@Override
	synchronized public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("i=" + (i--) + "threadName" 
		+ Thread.currentThread().getName());
	}

}
