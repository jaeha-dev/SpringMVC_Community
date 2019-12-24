package com.demo.controller;

import com.demo.service.CommentService;
import com.demo.service.PostService;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Git: jaeha-dev
 * @Name: 관리자 컨트롤러 클래스
 */
@Controller
@RequestMapping("/admin/*")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired private UserService userService;
    @Autowired private PostService postService;
    @Autowired private CommentService commentService;

    @PreAuthorize("hasRole('ROLE_M')")
    @GetMapping("/")
    public String getHomePage() {
        logger.info("getHomePage()");

        return "admin/home";
    }
}