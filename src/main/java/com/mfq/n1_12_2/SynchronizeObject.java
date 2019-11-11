package com.mfq.n1_12_2;

public class SynchronizeObject {
	synchronized public void printString() {
		System.out.println("begin");
		if (Thread.currentThread().getName().equals("a")) {
			System.out.println("a线程永远 suspend");
			System.out.println("当前线程: " + Thread.currentThread().getName());
			Thread.currentThread().suspend();
		}
		System.out.println("end");
	}

}
