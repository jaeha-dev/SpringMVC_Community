package com.demo.model;

import lombok.*;
import java.io.Serializable;

/**
 * @Git: jaeha-dev
 * @Memo: 댓글 추천 모델 클래스
 */
@Getter @Setter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private long postId; // 게시글 번호
    private long commentId; // 댓글 번호
    private String userNickname; // 댓글 추천자
}