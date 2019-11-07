package com.mfq.no1209;

import java.sql.Blob;

public class Run {
	public static void main(String[] args) {
		ALogin a = new ALogin();
		a.start();
		BLogin b = new BLogin();
		b.start();
	}

}
