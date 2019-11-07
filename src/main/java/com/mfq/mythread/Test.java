package com.mfq.mythread;

public class Test {
	
	public static void main(String[] args) {
		Mythread mythread = new Mythread();
		mythread.setName("mythread");
		mythread.start(); 
		for (int i = 0; i < 10000; i++) {
			System.out.println("main=" + Thread.currentThread().getName());
		}
	}

}
