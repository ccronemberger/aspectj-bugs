package com.example.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
//@Component  // use this without weaving (it should not be a component when using weaving)
//@Configurable // use this with weaving
public class SecuredMethodAspect2 implements InitializingBean /*, ConfigurableObject*/ {

    private AnyOtherBean anyOtherBean;

    /**
     * A private constructor is better when using weaving because creating instances with the constructor will not
     * work in this case. When weaving is used the correct way of getting an instance is via the aspectOf method.
     * Leaving this as public because then it works with and without weaving provided that we are using a factory
     * for the bean.
     */
    public SecuredMethodAspect2() {
        System.out.println("constructor " + getClass().getSimpleName() + " - " + hashCode());
        //printStackTrace();
    }

    //@Pointcut("@annotation(secured) && execution(* *(..))")
    public void coded_callAt(Secured secured) {
        // pointcut methods should be empty
    }

    @Around("@within(secured) && !@annotation(Secured) && execution(* *(..))")
    public Object coded_around2(ProceedingJoinPoint pjp,
                                Secured secured) throws Throwable {
        return coded_around(pjp, secured);
    }

    //@Around(value = "coded_callAt(secured)", argNames = "pjp,secured")
    @Around(value = "@annotation(secured) && execution(* *(..))", argNames = "pjp,secured")
    public Object coded_around(ProceedingJoinPoint pjp,
                         Secured secured) throws Throwable {
        //System.out.println(getClass().getSimpleName() + " - interceptor - initialized: " + (anyOtherBean != null) + " - " + hashCode());
        //printStackTrace();
        if (pjp.getArgs().length == 1) {
            Customer c = (Customer) pjp.getArgs()[0];
            c.setN(c.getN() + 1);
        }
        return pjp.proceed();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("bean initialized - " + (anyOtherBean != null) + " - " + hashCode());
    }

    @Autowired
    public void setAnyOtherBean(AnyOtherBean anyOtherBean) {
        System.out.println("setAnyOtherBean");
        //printStackTrace();
        this.anyOtherBean = anyOtherBean;
    }
}