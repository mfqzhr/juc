package com.mfq.n1_11_5;

public class Run2 {
	public static void main(String[] args) {
		try {
			Thread.currentThread().interrupt();
			System.out.println("是否停止1? = " + Thread.interrupted());
			System.out.println("是否停止2? = " + Thread.interrupted());
			System.out.println("是否停止3? = " + Thread.interrupted());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

} 
  