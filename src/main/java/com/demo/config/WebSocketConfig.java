package com.demo.config;

import com.demo.interceptor.ChatMessageInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import java.util.List;

/**
 * @Git: jaeha-dev
 * @Name: 웹 소켓 설정 클래스
 * @Memo: 서버 부하 감소 목적; 채널에 대기(Subscribe)하는 클라이언트에 비동기적으로 메시지를 전송(Publish)한다.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Autowired private ChatMessageInterceptor chatMessageInterceptor;

    /**
     * @Memo: 웹 소켓 서버로의 연결에 사용될 엔드 포인트 지정 (SockJS 는 웹 소켓을 지원하지 않는 구형 브라우저에 대한 옵션)
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        logger.info("registerStompEndpoints()");
        registry.addEndpoint("/ws-stomp").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        logger.info("configureMessageBroker()");
        registry.setApplicationDestinationPrefixes("/app"); // 클라이언트로부터 메시지를 전달 받을 주소의 Prefix
        registry.enableSimpleBroker("/topic"); // 메모리 기반의 메시지 브로커; 해당 주소를 구독하는 클라이언트로 메시지를 전달한다.
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        logger.info("configureClientInboundChannel()");

        // 사용자의 연결/연결 해제 시, 정보를 얻기 위해 인터셉터를 등록한다.
        registration.interceptors(chatMessageInterceptor);
    }

    @Override public boolean configureMessageConverters(List<MessageConverter> list) { return true; } // true 지정 (https://stackoverrun.com/ko/q/7060007)
    @Override public void configureWebSocketTransport(WebSocketTransportRegistration webSocketTransportRegistration) { }
    @Override public void configureClientOutboundChannel(ChannelRegistration channelRegistration) { }
    @Override public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) { }
    @Override public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) { }
}