package com.demo.repository;

import com.demo.model.User;
import com.demo.security.CustomUserDetails;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Git: jaeha-dev
 * @Name: 계정 레포지토리 클래스
 * @Memo: 메소드 순서는 INSERT, SELECT, UPDATE, DELETE 순서로 작성한다.
 * @Memo: 매개변수가 2개 이하 또는 모델 객체의 크기가 클 경우, Primitive 타입을 사용한다. 그외의 경우는 모델 또는 Map 객체를 사용한다.
 */
@Repository
@Transactional
public class UserRepository {
    private static final String namespace = "mappers.UserMapper";

    @Autowired private SqlSessionTemplate sql;

    // *--------------------------------------------------------------------------------------------------------------* [INSERT]

    /**
     * @TABLE: USER
     * @INSERT: 계정 등록
     */
    public int insertUser(User user) {
        return sql.insert(namespace + ".insertUser", user);
    }

    // *---------------------------------------------------------------------------------------------------------------* [SELECT]

    /**
     * @TABLE: USER
     * @SELECT: 이메일 중복 검사
     */
    public int countUserByEmail(String email) {
        return sql.selectOne(namespace + ".countUserByEmail", email);
    }

    /**
     * @TABLE: USER
     * @SELECT: 연락처 중복 검사
     */
    public int countUserByPhone(String phone) {
        return sql.selectOne(namespace + ".countUserByPhone", phone);
    }

    /**
     * @TABLE: USER
     * @SELECT: 닉네임 중복 검사
     */
    public int countUserByNickname(String nickname) {
        return sql.selectOne(namespace + ".countUserByNickname", nickname);
    }

    /**
     * @TABLE: USER
     * @SELECT: 전체 계정 개수 조회
     */
    public int countAllUser(boolean isEnabled) {
        return sql.selectOne(namespace + ".countAllUser", isEnabled);
    }

    /**
     * @TABLE: USER
     * @SELECT: 계정 목록 조회
     */
    public List<User> selectAllUser(boolean isEnabled) {
        return sql.selectList(namespace + ".selectAllUser", isEnabled);
    }

    /**
     * @TABLE: USER
     * @SELECT: 계정 상세 조회
     */
    public User selectUser(User user, boolean isRoleM) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("isRoleM", isRoleM);
        return sql.selectOne(namespace + ".selectUser", map);
    }

    /**
     * @TABLE: USER
     * @SELECT: 계정 로그인
     */
    public CustomUserDetails selectUserByEmail(String email) {
        return sql.selectOne(namespace + ".selectUserByEmail", email);
    }

    // *---------------------------------------------------------------------------------------------------------------* [UPDATE]

    /**
     * @TABLE: USER
     * @UPDATE: 계정 로그인 일자 수정
     */
    public int updateUserUpdatedAt(String email) {
        return sql.update(namespace + ".updateUserUpdatedAt", email);
    }

    /**
     * @TABLE: USER
     * @UPDATE: 계정 이메일 수정
     */
    public int updateUserEmail(User user) {
        return sql.update(namespace + ".updateUserEmail", user);
    }

    /**
     * @TABLE: USER
     * @UPDATE: 계정 이름 수정
     */
    public int updateUserName(User user) {
        return sql.update(namespace + ".updateUserName", user);
    }

    /**
     * @TABLE: USER
     * @UPDATE: 계정 연락처 수정
     */
    public int updateUserPhone(User user) {
        return sql.update(namespace + ".updateUserPhone", user);
    }

    /**
     * @TABLE: USER
     * @UPDATE: 계정 닉네임 수정
     */
    public int updateUserNickName(User user) {
        return sql.update(namespace + ".updateUserNickName", user);
    }

    /**
     * @TABLE: USER
     * @UPDATE: 계정 비밀번호 수정
     */
    public int updateUserPassword(User user) {
        return sql.update(namespace + ".updateUserPassword", user);
    }

    /**
     * @TABLE: USER
     * @UPDATE: 계정 수정
     */
    public int updateUser(User user, String userEmail) {
        System.out.println(user.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("userEmail", userEmail);
        return sql.update(namespace + ".updateUser", map);
    }

    /**
     * @TABLE: USER
     * @UPDATE: 계정 권한 수정
     */
    public int updateUserRole(User user) {
        return sql.update(namespace + ".updateUserRole", user);
    }

    /**
     * @TABLE: USER
     * @UPDATE: 계정 활성 수정
     */
    public int updateUserIsEnabled(User user, boolean isRoleM) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("isRoleM", isRoleM);
        return sql.update(namespace + ".updateUserIsEnabled", map);
    }

    // ----------------------------------------------------------------------------------------------------------------* [DELETE]
}