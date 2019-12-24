package com.demo.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Git: jaeha-dev
 * @Memo: 채팅방 모델 클래스
 */
@Getter @Setter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id; // 채팅방 번호

    @Pattern(regexp = "^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,100}$", message = "채팅방 이름 형식이 맞지 않습니다.")
    private String name; // 채팅방 이름

    private String userNickname; // 채팅방 개설자

    @Range(min = 2, max = 10, message = "채팅방 정원은 2~10명까지 가능합니다.")
    // @Pattern(regexp = "^([2-5]|10)$", message = "채팅방 정원은 1~5명까지 가능합니다.")
    private int maximum; // 채팅방 정원

    private Date createdAt; // 채팅방 개설 일자
    private Date updatedAt; // 채팅방 수정 일자
}