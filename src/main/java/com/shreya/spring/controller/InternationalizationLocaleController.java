package com.shreya.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    public class InternationalizationLocaleController {
        @RequestMapping("/locale")
        public String locale() {
            return "locale";
        }
    }
