package com.mfq.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/16 14:28
 * @description：
 * @modified By：
 * @version: $
 */
public class T03 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 26; i++) {
                    System.out.println((char) ('a' + i));
                    condition.signal();
                    condition.await();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 26; i++) {
                    System.out.println(i + 1);
                    condition.signal();
                    condition.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

    }
}
