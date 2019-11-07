package com.mythread.www;

public class Run3 {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread() {
				public void run() {
					try {
						Thread.sleep(5000000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.start();
			
		}
	}

}
