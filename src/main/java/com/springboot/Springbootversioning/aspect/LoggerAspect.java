package com.springboot.Springbootversioning.aspect;

import com.springboot.Springbootversioning.domain.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Aspect
@Component
@Order(1)
public class LoggerAspect {

    // declaring re usable point cut expression
    @Pointcut("execution(* com.springboot.Springbootversioning.controllers.*.*(..))")
    private void forControllersPackage(){}

    @Pointcut("execution(* com.springboot.Springbootversioning.repositoryImpl.*.get*(..))")
    private void forGetters(){}

    @Pointcut("execution(* com.springboot.Springbootversioning.repositoryImpl.*.set*(..))")
    private void forSetters(){}

    @Pointcut("forControllersPackage() && !(forGetters() || forSetters())")
    private void forDaoPackageNoGetterSetter() {}

    // point cut expression to match a method with 0 or more arguments of any type (..)
   // @Before(value="forControllersPackage() && !(forSetters() || forGetters())")
    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Logging Request for method "+ joinPoint.getSignature() + " started at " + new Date());
    }
    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics(JoinPoint joinPoint) {
        System.out.println("\n=====>>> Performing API analytics");
    }

//    @After(value="forControllersPackage()")
//    public void afterAdvice(JoinPoint joinPoint){
//        System.out.println("Logging Request for method "+ joinPoint.getSignature() + " started at " + new Date());
//    }
    @AfterReturning(pointcut = "execution(* com.springboot.Springbootversioning.repositoryImpl.AccountDAOImpl.findAccounts(..))",
                    returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result){
        System.out.println("@AfterReturning Method being executed is " + joinPoint.getSignature().toShortString());
        if(result != null && result.size()>0){
            System.out.println("result is " + result);
            System.out.println("***** Modified result is ");
            String modifiedValue = result.get(0).getLevel().toUpperCase();
            result.get(0).setLevel(modifiedValue);
            System.out.println(result);
        }
    }

    @AfterThrowing(pointcut = "execution(* com.springboot.Springbootversioning.repositoryImpl.AccountDAOImpl.findAccounts(..))",
            throwing = "exceptionRaised")
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Exception exceptionRaised){
        System.out.println("@AfterThrowing Method being executed is " + joinPoint.getSignature().toShortString());
        System.out.println("**** " + exceptionRaised);
    }


}
