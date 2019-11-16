package com.mfq.n2_1_4;

public class Run {

	public static void main(String[] args) {
		HasSelfPrivateNum num1 = new HasSelfPrivateNum();
		HasSelfPrivateNum num2 = new HasSelfPrivateNum();
		ThreadA threadA = new ThreadA(num1);	
		threadA.start();
		ThreadB threadB = new ThreadB(num2);
		threadB.start();
		
	}
}
