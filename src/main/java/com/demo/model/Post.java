package com.demo.model;

import lombok.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Git: jaeha-dev
 * @Memo: 게시글 모델 클래스
 */
@Getter @Setter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id; // 게시글 번호
    private String userNickname; // 게시글 작성자
    private String type; // 게시글 종류

    // 특수문자를 필터링하는 정규식 필요
    @Pattern(regexp = "^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,100}$", message = "게시글 제목 형식이 맞지 않습니다.")
    private String title; // 게시글 제목

    // 특수문자를 필터링하는 정규식 필요 (내용에 들어갈 태그는?)
    @Pattern(regexp = "^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,500}$", message = "게시글 내용 형식이 맞지 않습니다.")
    private String content; // 게시글 내용

    private Date createdAt; // 게시글 등록 일자
    private Date updatedAt; // 게시글 수정 일자
    private int comments; // 게시글 댓글수
    private int views; // 게시글 조회수
    private int likes; // 게시글 추천수
}