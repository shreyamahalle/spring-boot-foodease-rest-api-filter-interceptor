package com.shreya.spring.config;

import com.shreya.spring.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/api/**") // Protect only API routes
                .excludePathPatterns(
                        "/order.html",       // HTML page
                        "/order",            // Form submit POST path
                        "/css/**", "/js/**", "/images/**", "/static/**"
                ); // Allow static files
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
