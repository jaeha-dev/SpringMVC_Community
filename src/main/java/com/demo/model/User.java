package com.demo.model;

import lombok.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Git: jaeha-dev
 * @Memo: 계정 모델 클래스
 */
@Getter @Setter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id; // 계정 번호

    @Pattern(regexp="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식이 맞지 않습니다.")
    private String email; // 계정 이메일

    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호 형식이 맞지 않습니다.")
    // 계정 비밀번호: 8~15자, 최소 하나의 문자, 최소 하나의 숫자, 최소 하나의 특수 문자
    private String password; // 계정 비밀번호

    @Pattern(regexp="^[가-힣]{2,5}$", message = "이름 형식이 맞지 않습니다.")
    // 계정 이름: 한글만 허용(자음 또는 모음 비허용, 공백 비허용), 2~5자
    private String name; // 계정 이름

    @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$", message = "핸드폰 번호 형식이 맞지 않습니다.")
    // 계정 연락처: 핸드폰 번호 형식만 허용
    private String phone; // 계정 연락처

    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$", message = "닉네임 형식이 맞지 않습니다.")
    // 계정 닉네임: 2~10자, 영문 대소문자, 한글, 특수 문자
    // [\w]: [A-Za-z0-9_], [\W]: \w를 제외한 특수 문자
    // [ㄱ-ㅎ]: 자음, [ㅏ-ㅣ]: 모음, [가-힣]: 한글
    private String nickname; // 계정 닉네임

    private Date createdAt; // 계정 등록 일자
    private Date updatedAt; // 계정 로그인 일자
    private String role; // 계정 권한(ROLE_C, ROLE_B, ROLE_A, ROLE_M)
    private boolean isEnabled; // 계정 활성 상태(0: 사용 불가능, 1: 사용 가능)
}