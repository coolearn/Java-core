package com.core.concurrency;

public class ObjectAndClassLevelLock {


}

// 对象级锁定的各种实现方式
class DemoClass1 {
    public synchronized void demoMethod() {
    }
}

class DemoClass2 {
    public void demoMethod() {
        synchronized (this) {
            //other thread safe code
        }
    }
}

class DemoClass3 {
    private final Object lock = new Object();

    public void demoMethod() {
        synchronized (lock) {
            //other thread safe code
        }
    }
}

// 类级锁定的各种方法
class DemoClass4 {
    //Method is static
    public synchronized static void demoMethod() {
    }
}
class DemoClass5 {
    public void demoMethod() {
        //Acquire lock on .class reference
        synchronized (DemoClass5.class) {
            //other thread safe code
        }
    }
}
class DemoClass6 {
    private final static Object lock = new Object();
    public void demoMethod() {
        //Lock object is static
        synchronized (lock) {
            //other thread safe code
        }
    }
}