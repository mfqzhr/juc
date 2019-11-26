package com.mfq.n2_3_1;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		/**
		PrintString printString = new PrintString();
		new Thread(printString).start();
		System.out.println("我要停止它？ stopThread=" + Thread.currentThread().getName());
		printString.setContinuePrint(false);
		*/
		RunThread runThread = new RunThread();
		runThread.start();
		Thread.sleep(1000);
		runThread.setRunning(false);
		System.out.println("已经赋值为false");
	}

}
