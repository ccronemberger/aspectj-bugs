package com.example.aspectj;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
@Secured2
public class CustomerService2 implements CustomerServiceInterface2 {

    private TargetClass targetClass = new TargetClass();

    public CustomerService2() {
        System.out.println(getClass().getSimpleName());
    }

    @Transactional
    @Secured(isLocked = true)
    public void modifyCustomer(Customer customer) {
        // make sure there is a transaction
        if (customer.isUsesTx()) {
            TransactionAspectSupport.currentTransactionStatus();
        }

        customer.setN(customer.getN()+1);
        targetClass.targetMethod();
    }

    @Secured
    private void privateMethod() {
    }

    public void parallelMethodTx() {
        parallelMethodImpl();
    }

    @Secured
    public void parallelMethod() {
    }

    private void parallelMethodImpl() {
    }
}
