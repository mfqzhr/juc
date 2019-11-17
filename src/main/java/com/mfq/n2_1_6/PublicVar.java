package com.mfq.n2_1_6;

public class PublicVar {
	public String username = "A";
	public String password = "AA";
	synchronized public void setValue(String usernmae, String password) {
		try {
			this.username = username;
			Thread.sleep(50000);
			this.password = password;
			
			System.out.println("setValue method thread name=" 
					+ Thread.currentThread().getName() + " username=" + username + " password=" + password);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getValue() {
		System.out.println("getValue method thread name=" + Thread.currentThread().getName() + " username=" + username
				+ " password=" + password); 
	}
	

}
