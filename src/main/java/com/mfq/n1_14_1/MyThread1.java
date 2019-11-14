package com.mfq.n1_14_1;

public class MyThread1 extends Thread{
	@Override
	public void run() {
		System.out.println("线程1的优先级: " + getPriority());
		MyThread2 thread = new MyThread2();
		thread.start();
	}

}
