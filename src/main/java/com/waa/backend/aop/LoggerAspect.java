package com.waa.backend.aop;

import com.waa.backend.domains.LoggerEntry;
import com.waa.backend.services.LoggerService;
import com.waa.backend.util.AUTH;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Aspect
@Component
@RequiredArgsConstructor
public class LoggerAspect {
    private final LoggerService loggerService;

    @Pointcut("execution(* com.waa.backend.controllers..*(..))")
    public void packageExecution() {

    }

    @AfterThrowing(pointcut = "packageExecution()", throwing = "ex")
    public void handleException(JoinPoint joinPoint, Throwable ex) {
        LoggerEntry loggerEntry = LoggerEntry.builder()
                .userId(AUTH.getUserDetails().getId())
                .exceptionMessage(ex.getMessage())
                .details(joinPoint.toShortString())
                .build();
        loggerService.create(loggerEntry);
        System.out.println("Exception occurred: " + ex.getMessage());
    }

}
