package com.demo.service;

import com.demo.model.ChatRoom;
import com.demo.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Git: jaeha-dev
 * @Name: 채팅방 서비스 클래스
 */
@Service
public class ChatRoomService {

    @Autowired private ChatRoomRepository chatRoomRepository;

    /**
     * @INSERT: 채팅방 개설
     */
    public int insertChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.insertChatRoom(chatRoom);
    }

    /**
     * @SELECT: 채팅방 목록 조회
     */
    public List<ChatRoom> selectChatRoomList() {
        return chatRoomRepository.selectChatRoomList();
    }

    /**
     * @SELECT: 채팅방 상세 조회
     */
    public ChatRoom selectChatRoomById(String id) {
        return chatRoomRepository.selectChatRoomById(id);
    }

    /**
     * @SELECT: 채팅방 수정 및 삭제 본인 확인 검사
     */
    public int countChatRoomByIdAndUserNickname(String id, String userNickname) {
        return chatRoomRepository.countChatRoomByIdAndUserNickname(id, userNickname);
    }

    /**
     * @SELECT: 채팅방 정원 검사
     */
    public int selectChatRoomHeadcount(String id) {
        return chatRoomRepository.selectChatRoomHeadcount(id);
    }

    /**
     * @SELECT: 채팅방 이름 중복 검사
     */
    public int countChatRoomByName(String name) {
        return chatRoomRepository.countChatRoomByName(name);
    }

    /**
     * @UPDATE: 채팅방 수정
     */
    public int updateChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.updateChatRoom(chatRoom);
    }

    /**
     * @UPDATE: 채팅방 삭제
     */
    public int deleteChatRoom(String id, String owner) {
        return chatRoomRepository.deleteChatRoom(id, owner);
    }
}