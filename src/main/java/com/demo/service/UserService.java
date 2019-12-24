package com.demo.service;

import com.demo.model.User;
import com.demo.repository.UserRepository;
import com.demo.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Git: jaeha-dev
 * @Name: 계정 서비스 클래스
 */
@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    /**
     * @INSERT: 계정 등록
     */
    public int insertUser(User user) {
        // 비밀번호 암호화
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        return userRepository.insertUser(user);
    }

    /**
     * @SELECT: 이메일 중복 검사
     */
    public int countUserByEmail(String email) {
        return userRepository.countUserByEmail(email);
    }

    /**
     * @SELECT: 연락처 중복 검사
     */
    public int countUserByPhone(String phone) {
        return userRepository.countUserByPhone(phone);
    }

    /**
     * @SELECT: 닉네임 중복 검사
     */
    public int countUserByNickname(String nickname) {
        return userRepository.countUserByNickname(nickname);
    }

    /**
     * @SELECT: 전체 계정 개수 조회
     */
    public int countAllUser(boolean isEnabled) {
        return userRepository.countAllUser(isEnabled);
    }

    /**
     * @SELECT: 계정 목록 조회
     */
    public List<User> selectAllUser(boolean isEnabled) {
        return userRepository.selectAllUser(isEnabled);
    }

    /**
     * @SELECT: 계정 상세 조회
     */
    public User selectUser(User user, boolean isRoleM) {
        return userRepository.selectUser(user, isRoleM);
    }

    /**
     * @SELECT: 계정 로그인
     */
    public CustomUserDetails selectUserByEmail(String email) {
        return userRepository.selectUserByEmail(email);
    }

    /**
     * @UPDATE: 계정 로그인 일자 수정 (로그인 성공 핸들러에 의해 실행)
     */
    public void updateUserUpdatedAt(String email) {
        userRepository.updateUserUpdatedAt(email);
    }

    /**
     * @UPDATE: 계정 이메일 수정
     */
    public int updateUserEmail(User user) {
        return userRepository.updateUserEmail(user);
    }

    /**
     * @UPDATE: 계정 이름 수정
     */
    public int updateUserName(User user) {
        return userRepository.updateUserName(user);
    }

    /**
     * @UPDATE: 계정 연락처 수정
     */
    public int updateUserPhone(User user) {
        return userRepository.updateUserPhone(user);
    }

    /**
     * @UPDATE: 계정 닉네임 수정
     */
    public int updateUserNickName(User user) {
        return userRepository.updateUserNickName(user);
    }

    /**
     * @UPDATE: 계정 비밀번호 수정
     */
    public int updateUserPassword(User user) {
        return userRepository.updateUserPassword(user);
    }

    /**
     * @TABLE: USER
     * @UPDATE: 계정 수정
     */
    public int updateUser(User user, String userEmail) {
        return userRepository.updateUser(user, userEmail);
    }

    /**
     * @UPDATE: 계정 권한 수정
     */
    public int updateUserRole(User user) {
        return userRepository.updateUserRole(user);
    }

    /**
     * @UPDATE: 계정 활성 수정
     */
    public int updateUserIsEnabled(User user, boolean isRoleM) {
        return userRepository.updateUserIsEnabled(user, isRoleM);
    }
}