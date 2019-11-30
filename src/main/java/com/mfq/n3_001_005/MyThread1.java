package com.mfq.n3_001_005;

public class MyThread1 extends Thread{

	private Object lock;
	public MyThread1(Object lock) {
		// TODO Auto-generated constructor stub
		this.lock = lock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		synchronized (lock) {
			System.out.println("start wait time=" + System.currentTimeMillis());
			try {
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("end wait time=" + System.currentTimeMillis());
		}
	}
}
