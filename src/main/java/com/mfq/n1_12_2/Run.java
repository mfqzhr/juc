package com.mfq.n1_12_2;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		final SynchronizeObject object = new SynchronizeObject();
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				object.printString();
			}
		};
		thread1.setName("a");
		thread1.start();
		
		Thread.sleep(1000);
		Thread thread2 = new Thread() {
			public void run() {
				System.out.println("thread2启动了,但进入不了printString()方法! 只打印begin");
				System.out.println("因为printString()方法被a线程锁定并且永远暂停了");
				object.printString();
			};
		};
		thread2.start();
	}

}
