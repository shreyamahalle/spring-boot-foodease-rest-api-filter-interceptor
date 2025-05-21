package com.shreya.spring.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("Interceptor - PreHandle: {}", request.getRequestURI());
        logger.info("Request start time: {}", startTime);
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        logger.info("Interceptor - PostHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        logger.info("Interceptor - AfterCompletion");
        logger.info("Total request processing time {}",(System.currentTimeMillis() - startTime));

//        Object startTimeObj = request.getAttribute("startTime");
//        logger.info("Interceptor - AfterCompletion");
//
//        if (startTimeObj instanceof Long startTime) {
//            logger.info("Total request processing time: {} ms", (System.currentTimeMillis() - startTime));
//        } else {
//            logger.warn("Start time not found in request attributes.");
//        }
    }
}
