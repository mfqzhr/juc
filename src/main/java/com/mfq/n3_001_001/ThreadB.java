package com.mfq.n3_001_001;

import java.util.List;

public class ThreadB extends Thread{
	private MyList list;
	
	public ThreadB(MyList list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			while (true) {
				Thread.sleep(2000);
				if (list.size() == 5) {
					System.out.println("==5了,线程b要退出了!");
					throw new InterruptedException();
				}
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
