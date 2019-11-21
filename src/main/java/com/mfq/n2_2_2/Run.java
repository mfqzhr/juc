package com.mfq.n2_2_2;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		Task task = new Task();

		MyThread1 myThread1 = new MyThread1(task);

		myThread1.start();

		MyThread1 myThread2 = new MyThread1(task);

		myThread2.start();
		
		
	}
}
