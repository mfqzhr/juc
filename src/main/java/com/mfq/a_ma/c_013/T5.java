package com.mfq.a_ma.c_013;

import java.util.concurrent.Semaphore;

public class T5 {
	public static void main(String[] args) {
		Semaphore s = new Semaphore(2); //限流操作
		new Thread(() -> {
			try {
				s.acquire();
				System.out.println("t1 running");
				Thread.sleep(200);
				System.out.println("t1 running");
				s.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}).start();
		new Thread(() -> {
			try {
				s.acquire();
				System.out.println("t2 running");
				Thread.sleep(200);
				System.out.println("t2 running");
				s.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}).start();
	}

}
