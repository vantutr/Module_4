package com.codegym.customermanageaspect.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Logger {

    // Advice để ghi log khi phương thức trong service ném ra lỗi
    @AfterThrowing(pointcut = "execution(public * com.codegym.customermanageaspect.service.CustomerService.*(..))", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        System.out.printf("[CMS] ERROR in %s.%s%s: %s%n", className, methodName, args, e.getMessage());
    }

    // Advice để ghi log trạng thái bắt đầu và kết thúc của một phương thức
    @Around("execution(public * com.codegym.customermanageaspect.service.CustomerService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        System.out.printf("[CMS] LOG-BEFORE: Executing %s.%s()%n", className, methodName);

        // Thực thi phương thức gốc
        Object result = joinPoint.proceed();

        System.out.printf("[CMS] LOG-AFTER: Finished executing %s.%s()%n", className, methodName);

        return result;
    }
}
