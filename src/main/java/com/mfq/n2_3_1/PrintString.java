package com.mfq.n2_3_1;

public class PrintString implements Runnable{
	private boolean isContinuePrint = true;
	
	public boolean isContinuePrint() {
		return isContinuePrint;
	}
	
	public void setContinuePrint(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}
	
	public void printStringMethod() throws InterruptedException {
		while (isContinuePrint) {
			System.out.println("run printStringMethod threadName=" + Thread.currentThread().getName());
			Thread.sleep(1000);
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			printStringMethod();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
