package com.demo.controller;

import com.demo.model.ChatMessage;
import com.demo.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

/**
 * @Git: jaeha-dev
 * @Name: 채팅 메시지 컨트롤러 클래스
 */
@Controller
@RequiredArgsConstructor
public class ChatMessageController {
    private static final Logger logger = LoggerFactory.getLogger(ChatMessageController.class);

    @Autowired private ChatMessageService chatMessageService;

    /**
     * @URI: /chat/room/{id}
     * @MESSAGE: 메시지 전송
     */
    @MessageMapping("/chat/room/{id}")
    public void chatRoomMessage(@DestinationVariable("id") String id, @Payload ChatMessage chatMessage) {
        logger.info("chatRoomMessage()");

        chatMessageService.sendChatMessage(chatMessage);
    }
}