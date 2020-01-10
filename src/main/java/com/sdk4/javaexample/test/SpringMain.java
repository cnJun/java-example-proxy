package com.sdk4.javaexample.test;

import com.sdk4.javaexample.aop.AopTest;
import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AopTest aopTest = context.getBean(AopTest.class);
//        System.out.println("> AspectJ注解方式，方法不抛异常");
//        aopTest.sayHello(false);

//        System.out.println("> AspectJ注解方式，方法抛异常");
//        aopTest.sayHello(true);

//        System.out.println("> Xml配置，方法不抛异常");
//        aopTest.sayHi(false);
//
//        System.out.println("> Xml配置，方法抛异常");
//        aopTest.sayHi(true);

        String[] names = context.getBeanNamesForType(AspectJAwareAdvisorAutoProxyCreator.class);
        System.out.println(names[0]);
    }
}
