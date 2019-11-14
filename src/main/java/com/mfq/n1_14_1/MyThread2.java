package com.mfq.n1_14_1;

public class MyThread2 extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("线程2的优先级: " + getPriority());
	}

}
