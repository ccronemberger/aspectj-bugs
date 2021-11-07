package com.example.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyTargetAspect {
    @Around(value = "target(com.example.aspectj.CustomerService2) && execution(* *(..))") //
    public Object coded_around(ProceedingJoinPoint pjp) throws Throwable {
        //System.out.println("target join point");
        return pjp.proceed();
    }

    @Around(value = "target(com.example.aspectj.MainBean) && execution(* *(..))") //
    public Object coded_around2(ProceedingJoinPoint pjp) throws Throwable {
        //System.out.println("target join point.1");
        return pjp.proceed();
    }
}
