package com.mfq.n3_001_005;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();
		MyThread1 t1 = new MyThread1(lock);
		t1.start();
		Thread.sleep(3000);
		MyThread2 t2 = new MyThread2(lock);
		t2.start();
		
		
	}

}
