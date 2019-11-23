package com.mfq.n2_2_8;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		Task task = new Task();

		MyThread1 myThread1 = new MyThread1(task);

		myThread1.start();

		MyThread2 myThread2 = new MyThread2(task);

		myThread2.start();
		
		
	}
}
