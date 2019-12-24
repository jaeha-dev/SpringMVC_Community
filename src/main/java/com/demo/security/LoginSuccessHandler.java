package com.demo.security;

import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Git: jaeha-dev
 * @Name: 로그인 성공 핸들러 클래스
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    @Autowired private UserService userService;

    private String emailProperty;
    private String loginSuccessUriProperty;
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * @Memo: 로그인 성공 시, 핸들링하는 메소드
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // 로그인 성공 이전에 로그인 실패한 경우, 해당 로그인 실패에 대한 에러 세션을 삭제한다.
        clearLoginFailureSession(request);

        // 계정 로그인 일자를 갱신한다.
        String email = request.getParameter(emailProperty);
        userService.updateUserUpdatedAt(email);

        // 특정 URL 로 리다이렉트한다.
        redirectLoginSuccessUrl(request, response, authentication);
    }

    /**
     * @Memo: 로그인 성공 이전에 로그인 실패한 경우, 해당 에러 세션을 삭제하는 메소드
     */
    private void clearLoginFailureSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        // 에러 세션이 없을 경우
        if (session == null) {
            return;
        }
        // 에러 세션이 있을 경우 WebAttributes.AUTHENTICATION_EXCEPTION 이름의 세션을 삭제한다.
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    /**
     * @Memo: 로그인 성공 시, 특정 URL 로 리다이렉트하는 메소드
     */
    private void redirectLoginSuccessUrl(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Authentication authentication) throws IOException, ServletException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            // 로그인 이전에 방문한 페이지 주소가 있을 경우 (접근 권한을 요구하는 페이지에서 로그인 페이지로 이동된 경우)
            useSessionUrl(request, response);
        } else {
            // 직접 로그인 페이지에 접속한 경우 (로그인 버튼 클릭 등)
            useDefaultUrl(request, response);
        }
    }

    /**
     * @Memo: 로그인 이전에 방문한 페이지로 Redirect 하는 메소드
     */
    private void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String targetPath = savedRequest.getRedirectUrl();
        redirectStrategy.sendRedirect(request, response, targetPath);
    }

    /**
     * @Memo: loginSuccessUrl("/")로 Redirect 하는 메소드
     */
    private void useDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, loginSuccessUriProperty);
    }

    public String getEmailProperty() {
        return emailProperty;
    }

    public void setEmailProperty(String emailProperty) {
        this.emailProperty = emailProperty;
    }

    public String getLoginSuccessUriProperty() {
        return loginSuccessUriProperty;
    }

    public void setLoginSuccessUriProperty(String loginSuccessUriProperty) {
        this.loginSuccessUriProperty = loginSuccessUriProperty;
    }
}