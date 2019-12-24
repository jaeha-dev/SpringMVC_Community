package com.demo.repository;

import com.demo.model.ChatMessage;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Git: jaeha-dev
 * @Name: 채팅 메시지 레포지토리 클래스
 * @Memo: 메소드 순서는 INSERT, SELECT, UPDATE, DELETE 순서로 작성한다.
 * @Memo: 매개변수가 2개 이하 또는 모델 객체의 크기가 클 경우, Primitive 타입을 사용한다. 그외의 경우는 모델 또는 Map 객체를 사용한다.
 */
@Repository
@Transactional
public class ChatMessageRepository {
    private static final String namespace = "mappers.ChatMessageMapper";

    @Autowired private SqlSessionTemplate sql;

    /**
     * @INSERT: 채팅 메시지 등록
     */
    public int insertChatMessage(ChatMessage chatMessage) {
        return sql.insert(namespace + ".insertChatMessage", chatMessage);
    }

    /**
     * @SELECT: 채팅 메시지 목록 조회
     */
    public List<ChatMessage> selectChatMessageList() {
        return sql.selectList(namespace + ".selectChatMessageList");
    }

    /**
     * @SELECT: 채팅 메시지 상세 조회
     */
    public ChatMessage selectChatMessage(String id) {
        return sql.selectOne(namespace + ".selectChatMessage", id);
    }

    /**
     * @UPDATE: 채팅 메시지 삭제
     */
    public int deleteChatMessage(ChatMessage chatMessage) {
        return sql.delete(namespace + ".deleteChatMessage", chatMessage);
    }
}