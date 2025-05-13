package com.shreya.spring.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

        @Override
        public boolean preHandle (HttpServletRequest request,
                HttpServletResponse response,
                Object handler) throws Exception {
            logger.info("🔐 Interceptor - PreHandle: {}", request.getRequestURI());

            // Get Authorization header
            String authHeader = request.getHeader("Authorization");

            // Validate token presence and value
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.warn("Missing or invalid Authorization header");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized - Missing or invalid token");
                return false;
            }

            // Extract token (remove "Bearer " prefix)
            String token = authHeader.substring(7);

            // Dummy token validation - replace with real logic
            if (!token.equals("mysecrettoken")) {
                logger.warn("Invalid token: {}", token);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized - Invalid token");
                return false;
            }

            // Token is valid
            logger.info("Token validated successfully");
            return true;
        }
    }
