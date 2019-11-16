package com.mfq.n2_1_5;

public class MyObject {
	synchronized public void methodA() {
		try {
			System.out.println("begin methodA threadName=" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	synchronized public void methodB() {
		try {
			System.out.println("begin methodA threadName=" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
