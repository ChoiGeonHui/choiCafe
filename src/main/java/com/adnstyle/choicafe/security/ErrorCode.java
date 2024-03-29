package com.adnstyle.choicafe.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    COOKIE_NOT_FOUND(1000, "쿠키가 존재하지 않습니다."),
    INDEX_NOT_FOUND(1001, "인덱스가 존재하지 않습니다."),
    BOARD_NOT_FOUND(1002, "게시글을 찾을 수 없습니다."),
    UNKNOWN_ERROR(1003, "토큰이 존재하지 않습니다."),
    WRONG_TYPE_TOKEN(1004, "변조된 토큰입니다."),
    EXPIRED_TOKEN(1005, "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(1006, "지원하지 않는 토큰입니다."),
    ACCESS_DENIED(1007, "권한이 없습니다."),
    NO_IMAGE(1008, "이미지가 없습니다"),
    UNKEWN_REFERRER(1010,"출처가 불분명한 요청입니다.");


    private int code;
    private String message;


}
