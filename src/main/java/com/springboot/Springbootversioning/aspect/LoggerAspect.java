package com.springboot.Springbootversioning.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LoggerAspect {

    // point cut expression to match a method with 0 or more arguments of any type (..)
    @Before(value="execution(* com.springboot.Springbootversioning.controllers.CategoryController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Logging Request for method "+ joinPoint.getSignature() + " started at " + new Date());
    }
    @After(value="execution(* com.springboot.Springbootversioning.controllers.CategoryController.*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        System.out.println("Logging Request for method "+ joinPoint.getSignature() + " started at " + new Date());
    }

}
