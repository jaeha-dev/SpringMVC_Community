package com.demo.model.enums;

/**
 * @Git: jaeha-dev
 * @Name: 채팅 메시지 타입 클래스
 * @Memo: 채팅 메시지 타입은 입장, 대화, 퇴장으로 구분한다.
 */
public enum ChatMessageType {
    JOIN("JOIN"),
    TALK("TALK"),
    QUIT("QUIT");

    private final String value;

    private ChatMessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ChatMessageType valueOf(int value) {
        switch (value) {
            case 1 : return JOIN;
            case 2 : return TALK;
            case 3 : return QUIT;
            default: throw new AssertionError("Unknown ChatMessageType: " + value);
        }
    }
}