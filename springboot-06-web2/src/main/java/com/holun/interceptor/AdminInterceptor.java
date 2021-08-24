package com.holun.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uname = (String) request.getSession().getAttribute("uname");

        if (uname == null) {
            response.sendRedirect("index.html");
            return false;
        }

        return true;

    }
}
