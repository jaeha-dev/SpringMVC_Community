package com.demo.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Git: jaeha-dev
 * @Memo: 본인 확인 타입 어노테이션 클래스
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Identification {

    // 본인 확인 종류 (계정, 게시글, 댓글, 채팅방)
    public enum Type { USER, POST, COMMENT, CHAT_ROOM }

    public Type type() default Type.USER;
}