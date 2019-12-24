package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Git: jaeha-dev
 * @Name: 메인 컨트롤러 클래스
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public String getHomePage() {
        logger.info("getHomePage()");

        return "home";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/test")
    public void getTestPage() {
        logger.info("getTestPage()");

        // return "test";
    }
}