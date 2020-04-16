package com.mfq.test;

import com.mfq.n2_1_1.ThreadA;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/16 14:20
 * @description：
 * @modified By：
 * @version: $
 */

public class T02 {
    private static volatile Boolean isStartT2 = false;

    public static void main(String[] args) {
        final Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 26; i++) {
                    System.out.print((char) ('a' + i));
                    isStartT2 = true;
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();
        new Thread(() -> {

            synchronized (o) {
                while (!isStartT2) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 26; i++) {
                    System.out.print(i + 1);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
