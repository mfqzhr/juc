package com.mfq.test;

import com.mfq.n2_1_1.ThreadA;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/16 14:03
 * @description： 实现线程的顺序打印
 * @modified By：
 * @version: $
 */
public class T01 {
    public static Thread a = null;
    public static Thread b = null;

    public static void main(String[] args) {
        a = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.println((char) ('a' + i));
                LockSupport.unpark(b);
                LockSupport.park();
            }
            LockSupport.unpark(b);
        });

        b = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.println(i + 1);
                LockSupport.unpark(a);
                LockSupport.park();
            }
        });

        a.start();
        b.start();

    }
}
