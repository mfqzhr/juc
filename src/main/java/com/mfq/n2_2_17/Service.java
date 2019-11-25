package com.mfq.n2_2_17;

public class Service {
	public static void print(String stringParam) {
		// TODO Auto-generated method stub
		synchronized (stringParam) {
			while (true) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
