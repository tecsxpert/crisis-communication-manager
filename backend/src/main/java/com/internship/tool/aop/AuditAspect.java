package com.internship.tool.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    @Before("@annotation(auditLog)")
    public void log(JoinPoint joinPoint, AuditLog auditLog) {

        System.out.println("========= AUDIT LOG =========");
        System.out.println("Action: " + auditLog.value());
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("=============================");
    }
}