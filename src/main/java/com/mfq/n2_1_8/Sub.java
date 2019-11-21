package com.mfq.n2_1_8;

public class Sub extends Main{
	synchronized public void operateISubMethod() {
		try {
			while (i > 0) {
				i--;
				System.out.println("Sub pring i=" + i);
				Thread.sleep(100);
				super.operateIMainMethod();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
