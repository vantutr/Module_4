package com.codegym.thuchanh1.rest;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc)
            throws IOException, ServletException {
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Access Denied!");
    }
}