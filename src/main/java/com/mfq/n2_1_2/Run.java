package com.mfq.n2_1_2;

public class Run {
	public static void main(String[] args) {
		HasSelfPrivateNum h = new HasSelfPrivateNum();
		ThreadA a = new ThreadA(h);
		a.start();
		ThreadB b = new ThreadB(h);
		b.start();
	}

}
