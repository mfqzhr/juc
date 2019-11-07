package com.mfq.no1208;

public class MyThread3 extends Thread{
	private int count = 5;
	@Override
	synchronized public void run() {
		// TODO Auto-generated method stub
		count--;
		System.out.println("由" + this.currentThread().getName() + " 计算,count=" + count);
	}
	

}
