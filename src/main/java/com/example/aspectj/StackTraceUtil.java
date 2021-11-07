package com.example.aspectj;

import java.io.StringWriter;
import org.springframework.aop.framework.Advised;

public class StackTraceUtil {

    public static void showAdvised(Object object) {
        System.out.println("advised " + object.getClass().getSimpleName() + ": " + (object instanceof Advised));
    }

    public static void printStackTrace() {
        Exception ex = new Exception();
        StackTraceElement[] list = ex.getStackTrace();
        StringWriter sw = new StringWriter();
        sw.append("===================================\n");
        for (int i = list.length - 1; i >= 0; i--) {
            sw.append("    ").append(list[i].toString()).append('\n');
        }
        sw.append("=================================== " + list.length);
        System.out.println(sw.toString());
    }
}
