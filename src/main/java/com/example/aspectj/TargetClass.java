package com.example.aspectj;

import static com.example.aspectj.StackTraceUtil.printStackTrace;

public class TargetClass {
    public void targetMethod() {
        //System.out.println("target method");
        printStackTrace();
    }
}
