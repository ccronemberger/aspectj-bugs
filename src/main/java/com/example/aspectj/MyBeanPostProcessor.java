package com.example.aspectj;

import java.lang.reflect.Method;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MainBean) {
            Object obj = Enhancer.create(MainBean.class, new Interceptor(bean));
            System.out.println(obj.getClass().getName());
            return obj;
        }
        return bean;
    }

    private static class Interceptor implements MethodInterceptor {

        private Object target;

        public Interceptor(Object target) {
            this.target = target;
        }

        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return methodProxy.invoke(target, objects);
        }
    }
}
