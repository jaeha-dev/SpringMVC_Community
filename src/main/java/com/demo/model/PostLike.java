package com.demo.model;

import lombok.*;
import java.io.Serializable;

/**
 * @Git: jaeha-dev
 * @Memo: 게시글 추천 모델 클래스
 */
@Getter @Setter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private long postId; // 게시글 번호
    private String userNickname; // 게시글 추천자
}