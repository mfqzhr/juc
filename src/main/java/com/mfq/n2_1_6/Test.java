package com.mfq.n2_1_6;

public class Test {
		public static void main(String[] args) {
			PublicVar publicVar = new PublicVar();
			ThreadA threadA = new ThreadA(publicVar);
			threadA.start();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			publicVar.getValue();
		}
		
}
