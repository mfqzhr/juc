package com.mfq.a_ma;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author ac‘ 实现一个容器 提供 add size 方法 写两个线程, 线程1添加10个元素到容器中,线程2实现监控元素的个数,
 *         当个数到5个时,线程2给出提示并结束 无法实现
 */
public class WithOutVolatile {
	public volatile List<Integer> list = new ArrayList<>();

	public void add() {
		list.add(1);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		WithOutVolatile wov = new WithOutVolatile();
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				wov.add();
				System.out.println(i);
			}
		});
		Thread t2 = new Thread(() -> {
			while (true) {
				if (wov.size() == 5) {
					break;
				}
			}
			System.out.println("结束");
		});
		t1.start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();

	}

}
