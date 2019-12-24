package com.demo.model;

import lombok.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Git: jaeha-dev
 * @Memo: 채팅 메시지 모델 클래스
 */
@Getter @Setter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id; // 메시지 번호
    private long roomId; // 채팅방 번호

    private String type; // 메시지 종류
    private String userNickname; // 메시지 발송자

    @Pattern(regexp = "^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,100}$", message = "채팅 메시지는 1~100자 이내로 작성해야 합니다.")
    private String content; // 메시지 내용
}