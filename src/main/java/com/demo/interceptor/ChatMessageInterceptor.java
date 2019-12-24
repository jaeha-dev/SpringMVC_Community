package com.demo.interceptor;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * @Git: jaeha-dev
 * @Name: 채팅 메시지 인터셉터 클래스
 * @Memo: ChannelInterceptor 는 세션의 연결/연결 해제 시점을 필요로 할 때(사용자 동시 접속 관리), 사용한다.
 * (참고: https://gompangs.tistory.com/entry/Spring-boot-Websocket-connect-disconnect-관련)
 */
@Component
@RequiredArgsConstructor
public class ChatMessageInterceptor implements ChannelInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ChatMessageInterceptor.class);

    /**
     * @Memo: 전송 메시지에 대해 검사해야 하므로 preSend() 메소드를 구현한다.
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId(); // 세션 ID 구하기
        logger.info("Session ID : " + sessionId);

        logger.info("preSend(): " + Optional.ofNullable(accessor.getCommand().toString()).orElse("DEFAULT"));
        switch (accessor.getCommand()) {
            case CONNECT: // 연결 요청
                break;
            case SUBSCRIBE: // 메시지 구독; 채팅방 입장
                break;
            case DISCONNECT: // 연결 해제; 채팅방 퇴장
                break;
            default:
                break;
        }
        return message;
    }

    @Override public void postSend(Message<?> message, MessageChannel messageChannel, boolean b) { }
    @Override public void afterSendCompletion(Message<?> message, MessageChannel messageChannel, boolean b, Exception e) { }
    @Override public boolean preReceive(MessageChannel messageChannel) { return false; }
    @Override public Message<?> postReceive(Message<?> message, MessageChannel messageChannel) { return null; }
    @Override public void afterReceiveCompletion(Message<?> message, MessageChannel messageChannel, Exception e) { }
}
