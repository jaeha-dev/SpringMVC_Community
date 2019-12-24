package com.demo.service;

import com.demo.model.ChatMessage;
import com.demo.model.enums.ChatMessageType;
import com.demo.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * @Git: jaeha-dev
 * @Name: 채팅 메시지 서비스 클래스
 */
@Service
public class ChatMessageService {

    @Autowired private ChatMessageRepository chatMessageRepository;
    @Autowired private SimpMessageSendingOperations messagingTemplate;

    /**
     * @Memo: 채팅 메시지 전송
     */
    public void sendChatMessage(ChatMessage chatMessage) {
        if (ChatMessageType.JOIN.getValue().equals(chatMessage.getType())) {
            // 입장 메시지 알림
            chatMessage.setContent(chatMessage.getUserNickname() + "님이 들어왔습니다.");
            chatMessage.setUserNickname("[알림]");

        } else if (ChatMessageType.QUIT.getValue().equals(chatMessage.getType())) {
            // 퇴장 메시지 알림
            chatMessage.setContent(chatMessage.getUserNickname() + "님이 나갔습니다.");
            chatMessage.setUserNickname("[알림]");
        }

        messagingTemplate.convertAndSend("/topic/chat/room/" + chatMessage.getRoomId(), chatMessage);
    }
}