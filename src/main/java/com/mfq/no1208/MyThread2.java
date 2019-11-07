package com.mfq.no1208;

public class MyThread2 extends Thread{

	private int count = 5;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		count--;
		System.out.println("由" + this.currentThread().getName() + " 计算,count=" + count);
	}
}
