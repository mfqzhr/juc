package com.mfq.n2_3_1;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		/**
		PrintString printString = new PrintString();
		new Thread(printString).start();
		System.out.println("我要停止它？ stopThread=" + Thread.currentThread().getName());
		printString.setContinuePrint(false);
		
		RunThread runThread = new RunThread();
		runThread.start();
		Thread.sleep(1000);
		runThread.setRunning(false);
		System.out.println("已经赋值为false");
		*/
		
		Service service = new Service();
		ThreadA a = new ThreadA();
		a.start();
		Thread.sleep(1000);
		ThreadB b = new ThreadB();
		b.start();
		System.out.println("已经发起停止命令了");

	}

}
