package com.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Git: jaeha-dev
 * @Name: 로그인 실패 핸들러 클래스
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);

    private String emailProperty;
    private String passwordProperty;
    private String loginFailureMessageProperty;
    private String loginFailureUriProperty;

    /**
     * @Memo: 로그인 실패 시, 핸들링하는 메소드 (Forward 방식)
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String loginEmail = request.getParameter(emailProperty); // emailProperty 의 실제 값은 뷰에서 전달된 email 값이다.
        String loginPassword = request.getParameter(passwordProperty); // emailProperty 의 실제 값은 뷰에서 전달된 password 값이다.
        String failureMessage;

        // BadCredentialException -> 비밀번호가 일치하지 않은 경우
        // InternalAuthenticationServiceException -> 존재하지 않는 아이디일 경우
        // AuthenticationCredentialsNotFoundException -> 인증 요구가 거부되었을 경우
        // LockedException -> 인증 거부 : 잠긴 계정일 경우
        // DisabledException -> 인증 거부 : 계정 비활성화인 경우
        // AccountExpiredException -> 인증 거부 : 계정 유효 기간이 만료인 경우
        // CredentialExpiredException -> 인증 거부 : 비밀번호 유효 기간이 만료인 경우

        if (exception instanceof BadCredentialsException) {
            failureMessage = "이메일 또는 비밀번호를 확인해주세요.";
        } else if (exception instanceof UsernameNotFoundException) {
            failureMessage = "이메일 또는 비밀번호를 확인해주세요.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            failureMessage = "이메일 또는 비밀번호를 확인해주세요.";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            // 계정 비활성화 상태를 미인증 상태로 사용함.
            failureMessage = "가입 인증이 되지 않은 계정입니다.";
        } else {
            // 이외에 추가로 발생하는 예외에 대한 메시지
            failureMessage = "로그인 시, 문제가 발생하였습니다.";
        }

        // 로그인 실패 시, 로그인 폼에 이메일/비밀번호가 입력된 상태에, 오류 메시지를 출력하도록 한다.
        request.setAttribute(emailProperty, loginEmail);
        // request.setAttribute(passwordProperty, loginPassword); // 로그인 실패는 일반적으로 비밀번호 문제이므로 입력된 상태로 만들지 않는다.
        request.setAttribute(loginFailureMessageProperty, failureMessage);
        request.setAttribute("isLoginFailure", true); // header.jsp

        // Forward: 주소 변경(X), Request 공유(O; 이동된 페이지에서 Request, Session, Application 객체 사용 가능)
        // Redirect: 주소 변경(O), Request 공유(X; 이동된 페이지에서 Session, Application 객체 사용 가능)
        // Forward / Redirect 구분 필요성: 새로고침 버튼 등의 이벤트 발생으로 인한 데이터의 재 갱신에 있다.
        // Forward 시, 새로고침을 눌렀을 경우 페이지 이동이 없기 때문에 해당 페이지에서 만약 Insert, Update, Delete 쿼리문을 실행시켰다면 다시 재실행되기 때문에 에러가 발생할 수 있다.

        // 페이지 이동 (Forward 방식은 URL 주소 변경 없이 된다.)
        // 로그인 실패 시, 뷰에서 isAnonymous() 의 결과가 False 가 나오게 되어 로그인 버튼이 출력되지 않는 문제가 발생한다.
        // (request 객체에 loginFailure 값 추가로 해결)
        request.getRequestDispatcher(loginFailureUriProperty).forward(request, response);
    }

    public String getEmailProperty() {
        return emailProperty;
    }

    public void setEmailProperty(String emailProperty) {
        this.emailProperty = emailProperty;
    }

    public String getPasswordProperty() {
        return passwordProperty;
    }

    public void setPasswordProperty(String passwordProperty) {
        this.passwordProperty = passwordProperty;
    }

    public String getLoginFailureMessageProperty() {
        return loginFailureMessageProperty;
    }

    public void setLoginFailureMessageProperty(String loginFailureMessageProperty) {
        this.loginFailureMessageProperty = loginFailureMessageProperty;
    }

    public String getLoginFailureUriProperty() {
        return loginFailureUriProperty;
    }

    public void setLoginFailureUriProperty(String loginFailureUriProperty) {
        this.loginFailureUriProperty = loginFailureUriProperty;
    }
}