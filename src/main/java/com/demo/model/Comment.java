package com.demo.model;

import lombok.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Git: jaeha-dev
 * @Memo: 댓글 모델 클래스
 */
@Getter @Setter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id; // 댓글 번호
    private long postId; // 게시글 번호
    private String userNickname; // 댓글 작성자

    @Pattern(regexp = "^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,100}$", message = "댓글 형식이 맞지 않습니다.")
    private String content; // 댓글 내용

    private Date createdAt; // 댓글 등록 일자
    private Date updatedAt; // 댓글 수정 일자
    private int likes; // 댓글 추천수
}