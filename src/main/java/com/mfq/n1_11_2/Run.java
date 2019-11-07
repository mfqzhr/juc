package com.mfq.n1_11_2;

public class Run {
	public static void main(String[] args) {
		try {
			MyThread thread = new MyThread();
			thread.start();
			thread.sleep(1000);
			thread.interrupt();
			//Thread.currentThread().interrupt();
			System.out.println("是否停止1? = " + thread.interrupted());
			System.out.println("是否停止2? = " + thread.interrupted());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

} 
  