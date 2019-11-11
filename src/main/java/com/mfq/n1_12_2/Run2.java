package com.mfq.n1_12_2;


public class Run2 {
	public static void main(String[] args) throws InterruptedException {
		MyThread mythread = new MyThread();
		mythread.start();
		mythread.sleep(1000);
		mythread.suspend();
		System.out.println("main end");
		
	}

}
