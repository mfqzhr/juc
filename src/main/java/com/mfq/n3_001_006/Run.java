package com.mfq.n3_001_006;

public class Run {
	public static void main(String[] args) {
		try {
			Object lock = new Object();
			MyThread1 t1 = new MyThread1(lock);
			t1.start();
			Thread.sleep(50);
			MyThread2 t2 = new MyThread2(lock);
			t2.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
