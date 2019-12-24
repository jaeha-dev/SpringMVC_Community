package com.demo.model.enums;

/**
 * @Git: jaeha-dev
 * @Name: 게시글 타입 클래스
 * @Memo: 게시글 타입은 공지, 행사, 자유, 질문으로 구분한다.
 */
public enum PostType {
    NOTICE("공지"),
    EVENT("행사"),
    FREE("자유"),
    QNA("질문");

    private final String value;

    private PostType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PostType valueOf(int value) {
        switch (value) {
            case 1 : return NOTICE;
            case 2 : return EVENT;
            case 3 : return FREE;
            case 4 : return QNA;
            default: throw new AssertionError("Unknown PostType: " + value);
        }
    }
}