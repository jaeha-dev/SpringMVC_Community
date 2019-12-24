package com.demo.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Git: jaeha-dev
 * @Name: 스프링 시큐리티 계정 모델 클래스
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    // 테이블의 속성 이름과 일치해야 한다.
    private long id; // 계정 번호
    private String email; // 계정 이메일
    private String password; // 계정 비밀번호
    private String name; // 계정 이름
    private String phone; // 계정 연락처
    private String nickname; // 계정 닉네임
    private String created_at; // 계정 등록 일자
    private String updated_at; // 계정 로그인 일자
    private String role; // 계정 권한(ROLE_C, ROLE_B, ROLE_A, ROLE_M)
    private boolean is_enabled; // 계정 활성 상태(0: 사용 불가능, 1: 사용 가능)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(role));

        return authList;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @Memo: 계정 만료 여부를 판별하는 메소드(T: 만료 X, F: 만료 O)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // True 이므로 검사하지 않는다.
    }

    /**
     * @Memo: 계정 잠금 여부를 판별하는 메소드(T: 잠금 X, F: 잠금 O)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // True 이므로 검사하지 않는다.
    }

    /**
     * @Memo: 비밀번호 만료 여부를 판별하는 메소드(T: 만료 X, F: 만료 O)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // True 이므로 검사하지 않는다.
    }

    /**
     * @Memo: 계정 활성 여부를 판별하는 메소드(T: 활성 O, F: 활성 X)
     */
    @Override
    public boolean isEnabled() {
        return is_enabled;
    }
}
