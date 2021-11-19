package com.example.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class MyTargetAspect2 {

    public MyTargetAspect2() {
        System.out.println("constructor MyTargetAspect2");
    }

    // Tests done with java.version: 15.0.2

    // Case 1
    // With the weaver this causes a java.lang.VerifyError: Operand stack underflow.
    // With the compiler logs we can see it is trying to intercept the MainBean constructor.
    // 12 interceptions when executed, stack trace (in the TargetClass) with 108 elements.
    //@Around("target(com.example.aspectj.CustomerServiceInterface2+)")

    // Case 2
    // This weaves 74 join points in the logs. (advised by around advice from 'com.example.aspectj.MyTargetAspect2')
    // 2 interceptions, stack trace (in the TargetClass) with 52 elements.
    // With the compiler: 2 interceptions, stack trace (in the TargetClass) with 46 elements.
    @Around("target(com.example.aspectj.CustomerServiceInterface2+) && execution(* *(..))")

    // Case 3
    // This weaves 5 join points in the logs. (advised by around advice from 'com.example.aspectj.MyTargetAspect2')
    // 2 interceptions, stack trace (in the TargetClass) with 48 elements.
    // With the compiler: 2 interceptions, stack trace (in the TargetClass) with 42 elements.
    //@Around("execution(* com.example.aspectj.CustomerServiceInterface2+.*(..))")
    public Object codedAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(getClass().getSimpleName() + " interception");
        numberOfInterceptions = numberOfInterceptions + 1;
        return pjp.proceed();
    }

    private static int numberOfInterceptions = 0;

    public static int getNumberOfInterceptions() {
        return numberOfInterceptions;
    }
}
