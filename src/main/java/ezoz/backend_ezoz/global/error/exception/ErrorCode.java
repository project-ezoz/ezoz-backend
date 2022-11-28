package ezoz.backend_ezoz.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 인증
    ALREADY_REGISTERED_MEMBER(400, "이미 가입된 회원 입니다."),
    MISMATCHED_PASSWORD(401, "패스워드가 일치하지 않습니다."),
    LOGIN_ERROR(401, "아이디 또는 비밀번호를 확인해주세요"),

    // 인가
    FORBIDDEN_ADMIN(403, "관리자 권한이 없습니다."),

    //토큰
    INVALID_MEMBER_TYPE(400, "잘못된 회원 타입 입니다.(memberType : KAKAO)"),
    NOT_EXISTS_AUTHORIZATION(401, "Authorization Header가 빈값입니다."),
    NOT_VALID_BEARER_GRANT_TYPE(401, "인증 타입이 Bearer 타입이 아닙니다."),
    NOT_VALID_TOKEN(401, "유효하지않은 토큰 입니다."),
    TOKEN_EXPIRED(401, "해당 토큰은 만료됐습니다."),
    ACCESS_TOKEN_EXPIRED(401, "해당 access token은 만료됐습니다."),
    NOT_ACCESS_TOKEN_TYPE(401, "tokenType이 access token이 아닙니다."),
    REFRESH_TOKEN_EXPIRED(401, "해당 refresh token은 만료됐습니다."),
    REFRESH_TOKEN_NOT_FOUND(400, "해당 refresh token은 존재하지 않습니다."),
    UNSUPPORTED_TOKEN(401, "지원되지 않는 JWT 토큰입니다."),
    INVALID_TOKEN_SIGNATURE(401, "잘못된 JWT 서명입니다."),

    // 회원
    MEMBER_NOT_EXISTS(400, "해당 회원은 존재하지 않습니다."),

    // 이미지
    FAILED_REGISTER_IMAGE(400, "이미지가 저장되지 않았습니다."),
    IMAGE_NOT_EXISTS(400, "첨부된 이미지가 없습니다."),

    // 저널
    JOURNAL_NOT_EXISTS(400, "해당 저널은 존재하지 않습니다."),
    INVALID_JOURNAL_TYPE(400, "잘못된 Journal Type입니다."),

    // 포스트
    POST_NOT_EXISTS(400, "해당 포스트는 존재하지 않습니다.");


    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;
}
