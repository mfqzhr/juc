package com.mfq.n1_11_5;


public class Run3 {
	public static void main(String[] args) {
		try {
			MyThread mythread = new MyThread();
			mythread.start();
			Thread.sleep(1000);
			mythread.interrupt();
			System.out.println("是否停止1? = " + mythread.isInterrupted());
			System.out.println("是否停止2? = " + mythread.isInterrupted());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

} 
  