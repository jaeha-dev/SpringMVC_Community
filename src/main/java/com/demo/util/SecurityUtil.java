package com.demo.util;

import com.demo.model.User;
import com.demo.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

/**
 * @Git: jaeha-dev
 * @Name: 스프링 시큐리티 유틸 클래스
 * @Memo: 스프링 시큐리티 사용 중, 중복된 로직을 추출한 클래스이다.
 */
public class SecurityUtil {

    /**
     * @Memo: 컨트롤러 메소드에서 사용자 정보를 얻기 위한 중복된 매개변수(@AuthenticationPrincipal) 추출
     * @Memo: Thread-safe (참고: https://stackoverflow.com/questions/13910976/how-to-ensure-thread-safety-of-utility-static-method, https://stackoverflow.com/questions/26379290/security-util-using-securitycontextholder-in-spring-application)
     * @Memo: Null 예방 (참고: https://www.daleseo.com/java8-optional-after, https://multifrontgarden.tistory.com/131, http://blog.naver.com/PostView.nhn?blogId=hehe5959&logNo=221002524184&redirect=Dlog&widgetTypeCall=true)
     */
    public static CustomUserDetails getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Optional.ofNullable(principal)
        //         .filter(obj -> obj instanceof CustomUserDetails) // if 조건문
        //         .map(CustomUserDetails.class::cast) // 형변환 후, return
        //         .orElseGet(CustomUserDetails::new).setNickname("익명"); // null 또는 형변환 실패 시, 익명 닉네임을 가진 객체

        return Optional.ofNullable(principal)
                .filter(obj -> obj instanceof CustomUserDetails) // if 조건문
                .map(CustomUserDetails.class::cast) // 형변환 후, return
                .orElseGet(CustomUserDetails::new); // null 또는 형변환 실패 시, 빈 객체
    }

    /**
     * @Memo: UserDetails -> User 변환 (계정 번호, 생성/수정 일자 불필요)
     * @param userDetails
     */
    public static User convertUserDetailsToUser(CustomUserDetails userDetails) {
        return User.builder().email(userDetails.getEmail())
                .name(userDetails.getName())
                .phone(userDetails.getPhone())
                .nickname(userDetails.getNickname())
                .role(userDetails.getRole())
                .isEnabled(userDetails.is_enabled()).build();
    }
}