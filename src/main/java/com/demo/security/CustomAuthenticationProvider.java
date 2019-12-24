package com.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Git: jaeha-dev
 * @Name: 스프링 시큐리티 인증 프로바이더 클래스
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired private CustomUserDetailsService userDetailsService;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        String email = String.valueOf(authenticationToken.getPrincipal());
        String password = String.valueOf(authenticationToken.getCredentials());

        // 계정을 조회한다.
        UserDetails userAuth = userDetailsService.loadUserByUsername(email);

        try {
            // 평문과 암호화된 비밀번호 검사
            if (! passwordEncoder.matches(password, userAuth.getPassword())) {
                throw new BadCredentialsException(email);
            }
            // 계정 활성, 잠금 여부 검사
            // if (! userAuth.isEnabled() || userAuth.isAccountNonLocked()) {
            //     throw new AuthenticationCredentialsNotFoundException(email);
            // }

            // 계정 활성 여부 검사
            if (! userAuth.isEnabled()) {
                throw new AuthenticationCredentialsNotFoundException(email);
            }
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (AuthenticationCredentialsNotFoundException e) {
            throw new AuthenticationCredentialsNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new UsernamePasswordAuthenticationToken(userAuth, password, userAuth.getAuthorities());
    }

    /**
     * @Memo: 로그인 폼에서 login-processing-url 로의 POST 요청 시, 해당 클래스의 supports() 메소드, authenticate() 메소드 순서로 인증 절차 진행
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
        // return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}