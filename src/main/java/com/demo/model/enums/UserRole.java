package com.demo.model.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Git: jaeha-dev
 * @Name: 계정 권한 타입 클래스
 * @Memo: 계정 권한 타입은 C등급, B등급, A등급, M등급(관리자)으로 구분한다.
 */
public enum UserRole implements GrantedAuthority {
    ROLE_C("ROLE_C"),
    ROLE_B("ROLE_B"),
    ROLE_A("ROLE_A"),
    ROLE_M("ROLE_M");

    private String role;

    private UserRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }

    public static UserRole valueOf(int value) {
        switch (value) {
            case 1 : return ROLE_C;
            case 2 : return ROLE_B;
            case 3 : return ROLE_A;
            case 4 : return ROLE_M;
            default: throw new AssertionError("Unknown UserRole: " + value);
        }
    }
}