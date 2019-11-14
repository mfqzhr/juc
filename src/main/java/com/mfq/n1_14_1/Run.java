package com.mfq.n1_14_1;

public class Run {
	public static void main(String[] args) {
		Thread.currentThread().setPriority(6);
		System.out.println("主线程的优先级: " + Thread.currentThread().getPriority());
		MyThread1 thread = new MyThread1();
		thread.start();
	}

}
