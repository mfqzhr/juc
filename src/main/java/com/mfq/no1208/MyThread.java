package com.mfq.no1208;

public class MyThread extends Thread{
	private int count = 5;
	public MyThread (String name) {
		this.setName(name); //设置线程的名称
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (count > 0) {
			count--;
			System.out.println("由" + this.currentThread().getName() + " 计算,count=" + count);
		}
	}

}
