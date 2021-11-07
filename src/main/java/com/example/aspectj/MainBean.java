package com.example.aspectj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Secured
public class MainBean {

    public MainBean() {
    }

    @Autowired
    private CustomerServiceInterface2 service;

    public void run() {
        Customer c = new Customer();
        c.setUsesTx(true);
        c.setN(0);
        System.out.println("before the call");
        service.modifyCustomer(c);
        CustomerService2 cs = new CustomerService2();
        c.setUsesTx(false); // even if the interception happens, because this is not called via Spring the TX will not be started
        cs.modifyCustomer(c); // this is not intercepted if not running with AspectJ
        System.out.println("end");
    }
}
