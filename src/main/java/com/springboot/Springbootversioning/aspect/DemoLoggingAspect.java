package com.springboot.Springbootversioning.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class DemoLoggingAspect {

    @Around("execution(* com.springboot.Springbootversioning.serviceImpls.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);
        // get begin timestamp
        long begin = System.currentTimeMillis();
        // now, let's execute the method
        Object result = null;
        try{
         result = proceedingJoinPoint.proceed();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return  result;
    }
    @AfterThrowing(pointcut = "execution(* com.springboot.Springbootversioning.repositoryImpl.AccountDAOImpl.findAccounts(..))",
            throwing = "exceptionRaised")
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable exceptionRaised){
         String method = joinPoint.getSignature().toShortString();
        System.out.println("Executing @AfterThrowing on method: " + method);
        System.out.println("Exception is " + exceptionRaised);
    }
    @After("execution(* com.springboot.Springbootversioning.repositoryImpl.AccountDAOImpl.findAccounts(..))")
    public void afterFindAccountsAdvice(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
    }

}
