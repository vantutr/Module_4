package com.codegym.baitap2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    private int visitCounter = 0;

    // Pointcut để bắt các phương thức thay đổi trạng thái sách
    private static final String BOOK_STATE_CHANGE_POINTCUT =
            "execution(* com.codegym.baitap2.service.BookService.borrowBook(..)) || " +
                    "execution(* com.codegym.baitap2.service.BookService.returnBook(..))";

    // Pointcut để bắt tất cả các hành động trong Controller
    private static final String ALL_CONTROLLER_ACTIONS_POINTCUT =
            "within(com.codegym.baitap2.controller.*)";

    @After(BOOK_STATE_CHANGE_POINTCUT)
    public void logBookStateChange(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[LOG] Trang thai sach đa thay doi boi phuong thuc: " + methodName);
    }

    @Before(ALL_CONTROLLER_ACTIONS_POINTCUT)
    public void logVisit(JoinPoint joinPoint) {
        visitCounter++;
        String methodName = joinPoint.getSignature().getName();
        System.out.printf("[LOG] Luot truy cap thu %d. Phuong thuc duoc goi: %s%n", visitCounter, methodName);
    }
}
