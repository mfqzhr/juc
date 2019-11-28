package com.mfq.n3_001_001;

import java.util.ArrayList;
import java.util.List;

public class MyList {
	volatile private List list = new ArrayList();
	public void add() {
		// TODO Auto-generated method stub
		list.add("mfq");

	}
	public int size() {
		return list.size();
	}

}
