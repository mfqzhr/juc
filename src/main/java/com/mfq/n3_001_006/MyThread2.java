package com.mfq.n3_001_006;

public class MyThread2 extends Thread{

	private Object lock;
	public MyThread2(Object lock) {
		// TODO Auto-generated constructor stub
		this.lock = lock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					MyList.add();
					if (MyList.size() == 5) {
						lock.notify();
						System.out.println("已经发出通知!");
					}
					System.out.println("添加了" + (i + 1) + "个元素");
					Thread.sleep(1000);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
