package com.mfq.n2_1_4;

public class HasSelfPrivateNum {
	private int num = 0; //线程不安全
	synchronized public void addI(String username) {
		//int num = 0;//线程安全
		try {
			if (username.equals("a")) {
				System.out.println("a start");
				num = 100;
				System.out.println("a set over");
				Thread.sleep(2000);

			} else {
				System.out.println("b start");
				num = 200;
				System.out.println("b set over");
			}
			System.out.println(username + " num= " + num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
