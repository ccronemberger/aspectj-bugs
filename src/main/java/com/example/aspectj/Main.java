package com.example.aspectj;

import javax.sql.DataSource;
import org.aspectj.lang.Aspects;
import org.aspectj.lang.NoAspectBoundException;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

// https://stackoverflow.com/questions/54749106/aspectj-ltw-weaving-not-working-with-spring-boot
@EnableAspectJAutoProxy(exposeProxy = false, proxyTargetClass = true)
@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@ComponentScan
public class Main implements TransactionManagementConfigurer {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        System.out.println("java.version: " + System.getProperty("java.version"));
        System.out.println("java.home: " + System.getProperty("java.home"));

        applicationContext =
            new AnnotationConfigApplicationContext(Main.class);
        Main main = applicationContext.getBean(Main.class);
        main.run(args);
        System.out.println("number of interceptions: " + MyTargetAspect2.getNumberOfInterceptions());
    }

    private void run(String[] args) throws Exception {
        Runner runner = new Runner();
        runner.run(args);
    }

    @Autowired
    private MainBean mainBean;

    @Bean
    public DataSource h2DataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:mem:testdb");
        return ds;
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(h2DataSource());
    }

    @Bean
    public MyTargetAspect2 myTargetAspect2()
        throws IllegalAccessException, InstantiationException
    {
        return getAspectInstance(MyTargetAspect2.class);
    }

    @Bean
    public SecuredMethodAspect securedMethodAspect()
        throws IllegalAccessException, InstantiationException
    {
        return getAspectInstance(SecuredMethodAspect.class);
    }

    @Bean
    public SecuredMethodAspect2 securedMethodAspect2()
        throws IllegalAccessException, InstantiationException
    {
        return getAspectInstance(SecuredMethodAspect2.class);
    }

    @Bean
    public SecuredMethodAspect3 securedMethodAspect3()
        throws IllegalAccessException, InstantiationException
    {
        return getAspectInstance(SecuredMethodAspect3.class);
    }

    @Bean
    public SecuredMethodAspect4 securedMethodAspect4()
        throws IllegalAccessException, InstantiationException
    {
        return getAspectInstance(SecuredMethodAspect4.class);
    }

    @Bean
    public SecuredMethodAspect5 securedMethodAspect5()
        throws IllegalAccessException, InstantiationException
    {
        return getAspectInstance(SecuredMethodAspect5.class);
    }

    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return txManager();
    }

    private class Runner {

        public void run(String... args) throws Exception {
            if (false) {
                String[] names = applicationContext.getBeanDefinitionNames();
                for (String name : names) {
                    Object obj = applicationContext.getBean(name);
                    Class<?>[] interfaces = obj.getClass()
                                               .getInterfaces();
                    System.out.println(name);
                    for (Class cls : interfaces) {
                        System.out.println("  " + cls.getName());
                    }
                }
            }
            mainBean.run();
        }
    }

    private <T> T getAspectInstance(Class<T> cls)
        throws IllegalAccessException, InstantiationException {
        try {
            return Aspects.aspectOf(cls);
        } catch (NoAspectBoundException ex) {
            System.err.println("Warning aspectOf method was not found, creating instance with constructor. "
                + "This is a problem if the application is using AspectJ weaving.");
            return cls.newInstance();
        }
    }
}
