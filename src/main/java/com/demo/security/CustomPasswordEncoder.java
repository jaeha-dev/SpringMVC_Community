package com.demo.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Git: jaeha-dev
 * @Name: 비밀번호 암호화 클래스
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    private PasswordEncoder passwordEncoder;

    public CustomPasswordEncoder() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * @Memo: 비밀번호 암호화 메소드
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * @Memo: 평문과 암호화된 비밀번호의 일치 여부를 판별하는 메소드
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}