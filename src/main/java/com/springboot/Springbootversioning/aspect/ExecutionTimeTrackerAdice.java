package com.springboot.Springbootversioning.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExecutionTimeTrackerAdice {

    Logger logger = LoggerFactory.getLogger(ExecutionTimeTrackerAdice.class);

    @Around("@annotation(com.springboot.Springbootversioning.aspect.TrackExecutionTime)")
    public Object trackTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Method name " + proceedingJoinPoint.getSignature() + " took time to execute of: " + (endTime - startTime) + " milli seconds");
        return obj;
    }

}
