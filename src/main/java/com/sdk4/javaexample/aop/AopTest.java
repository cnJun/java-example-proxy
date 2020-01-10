package com.sdk4.javaexample.aop;

public class AopTest {

    public void sayHello(boolean throwExcetion) throws Exception {
        if (throwExcetion) {
            throw new Exception("say hello fail");
        }

        System.out.println("say hello.");
    }

    public void sayHi(boolean throwExcetion) throws Exception {
        if (throwExcetion) {
            throw new Exception("say hi fail");
        }

        System.out.println("say hi.");
    }

}
