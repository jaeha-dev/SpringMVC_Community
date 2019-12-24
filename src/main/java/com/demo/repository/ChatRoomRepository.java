package com.demo.repository;

import com.demo.model.ChatRoom;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Git: jaeha-dev
 * @Name: 채팅방 레포지토리 클래스
 * @Memo: 메소드 순서는 INSERT, SELECT, UPDATE, DELETE 순서로 작성한다.
 * @Memo: 매개변수가 2개 이하 또는 모델 객체의 크기가 클 경우, Primitive 타입을 사용한다. 그외의 경우는 모델 또는 Map 객체를 사용한다.
 */
@Repository
@Transactional
public class ChatRoomRepository {
    private static final String namespace = "mappers.ChatRoomMapper";

    @Autowired private SqlSessionTemplate sql;

    /**
     * @INSERT: 채팅방 개설
     */
    public int insertChatRoom(ChatRoom chatRoom) {
        return sql.insert(namespace + ".insertChatRoom", chatRoom);
    }

    /**
     * @SELECT: 채팅방 목록 조회
     */
    public List<ChatRoom> selectChatRoomList() {
        return sql.selectList(namespace + ".selectChatRoomList");
    }

    /**
     * @SELECT: 채팅방 상세 조회
     */
    public ChatRoom selectChatRoomById(String id) {
        return sql.selectOne(namespace + ".selectChatRoom", id);
    }

    /**
     * @SELECT: 채팅방 수정 및 삭제 본인 확인 검사
     */
    public int countChatRoomByIdAndUserNickname(String id, String userNickname) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userNickname", userNickname);
        return sql.selectOne(namespace + ".countChatRoomByIdAndUserNickname", map);
    }

    /**
     * @SELECT: 채팅방 정원 검사
     */
    public int selectChatRoomHeadcount(String id) {
        return sql.selectOne(namespace + ".selectChatRoomHeadcount", id);
    }

    /**
     * @SELECT: 채팅방 이름 중복 검사
     */
    public int countChatRoomByName(String name) {
        return sql.selectOne(namespace + ".countChatRoomByName", name);
    }

    /**
     * @UPDATE: 채팅방 수정
     */
    public int updateChatRoom(ChatRoom chatRoom) {
        return sql.update(namespace + ".updateChatRoom", chatRoom);
    }

    /**
     * @DELETE: 채팅방 삭제
     */
    public int deleteChatRoom(String id, String userNickname) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userNickname", userNickname);
        return sql.delete(namespace + ".deleteChatRoom", map);
    }
}