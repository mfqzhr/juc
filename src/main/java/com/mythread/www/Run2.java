package com.mythread.www;

public class Run2 {
	public static void main(String[] args) throws InterruptedException {
		MyThread myThread = new MyThread();
		myThread.start();
		Thread.sleep(200);
		System.out.println("运行结束");
	}

}
