package com.mfq.no1209;

public class BLogin extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		LoginServlet.doPost("b", "bb");
	}
}
