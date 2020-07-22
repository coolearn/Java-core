package com.core.concurrency;

import javax.servlet.*;
import java.math.BigInteger;

public class ThreadSafety {

    // 什么是线程安全 ？
    // 指某个函数、函数库在多线程环境中被调用时，能够正确的处理多个线程之间的共享变量，是程序功能正确完成。
    // 无状态的对象是线程安全的

}

// 案例：无状态（没有字段和引用、也没有引用其他类）
//class StatelessFactorizer implements Servlet {
//    public void service(ServletRequest req, ServletResponse resp)
//    {
//        BigInteger i = extractFromRequest(req);
//        BigInteger[] factors = factor(i);
//        encodeIntoResponse(resp, factors);
//    }
//}

class Stateless {
    void test() {
        System.out.println("Test!");
    }
}

class Stateless1 {
    //No static modifier because we're talking about the object itself
    final String TEST = "Test!";
    void test() {
        System.out.println(TEST);
    }
}

/**
 * 该类具有状态，不属于无状态类，但是他的状态只会设置一次，之后不会发生改变，
 * 这种类型的变量称为不可变的
 */
class Immutable {
    final String testString;
    Immutable(String testString) {
        this.testString = testString;
    }
    void test() {
        System.out.println(testString);
    }
}

/**
 * 无状态类，线程安全
 */
class MathUtils {
    public static BigInteger factorial(int number) {
        BigInteger f = new BigInteger("1");
        for (int i = 2; i <= number; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }
}

/**
 * 有状态类,非线程安全
 */
class StateClass {
    private int i = 1 ;
    public void test(){
        i++ ;
    }
}

