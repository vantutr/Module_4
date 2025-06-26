package com.example.nasa_hibernate.logger;

import com.example.nasa_hibernate.exception.BadFeedbackException;
import com.example.nasa_hibernate.model.Feedback;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class FeedbackLogger {
    private static final Logger logger = LoggerFactory.getLogger(FeedbackLogger.class);

    @AfterThrowing(pointcut = "execution(* com.example.nasa_hibernate.controller.FeedbackController.submit(..))", throwing = "ex")
    public void logBadFeedback(JoinPoint joinPoint, BadFeedbackException ex) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Feedback feedback) {
                logger.warn("Blocked bad feedback - Author: {}, Comment: '{}', Date: {}",
                        feedback.getAuthor(), feedback.getComment(), new Date());
            }
        }
    }
}
