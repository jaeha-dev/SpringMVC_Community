package com.demo.security;

import com.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Git: jaeha-dev
 * @Name: 스프링 시큐리티 계정 서비스 클래스
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired private UserRepository userRepository;

    /**
     * @Memo: 계정 정보를 이용하여 DataSource 에서 계정을 조회한다.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomUserDetails user = null;

        try {
            user = userRepository.selectUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 계정 조회 실패 또는 접근 권한 없음
        if (user == null || user.getAuthorities().size() == 0) {
            throw new UsernameNotFoundException(email);
        }

        return user;
    }
}