package com.core.concurrency;

public class SynchronizedKeyword {

    public static void main(String args[]) {

        final MathClass mathClass = new MathClass();
        //first thread
        Runnable r = new Runnable() {
            public void run() {
                try {
                    mathClass.printNumbers1(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r, "ONE").start();
        new Thread(r, "TWO").start();
    }
}

class MathClass {

    void printNumbers(int n) throws InterruptedException {
        synchronized (this) { // 代码块
            for (int i = 1; i <= n; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i);
                Thread.sleep(500);
            }
        }
    }

    synchronized void printNumbers1(int n) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            System.out.println(Thread.currentThread().getName() + " :: " + i);
            Thread.sleep(500);
        }
    }
}
