package ezoz.backend_ezoz.api.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OauthLoginDto {
    @Getter
    @ApiModel(value = "서버 토큰 발급 API 요청 값", description = "서버 토큰 발급 요청 값입니다.")
    public static class Request {
        @ApiModelProperty(value = "회원 소셜 타입", required = true, example = "GOOGLE")
        private String memberType;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "서버 토큰 발급 API 응답 값", description = "서버 토큰 발급 응답 값입니다.")
    public static class Response {

        @ApiModelProperty(value = "액세스 토큰", required = true, example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBQ0NFU1MiLCJhdWQiOiJlem96OTA1NjNAZ21haWwuY29tIiwiaWF0IjoxNjYzNzYxMTQ1LCJleHAiOjE2NjM3NjIwNDUsInJvbGUiOiJVU0VSIn0.daqncRaD45I7rsVzWzwPpcGLQwrEBLwkhlLzX5CFzNPDnT-QRqpslrtGMPRmrUPoXn46umOTBGbvGYNTjwiEZw")
        private String accessToken;

        @ApiModelProperty(value = "리프레쉬 토큰", required = true, example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSRUZSRVNIIiwiYXVkIjoiZXpvejkwNTYzQGdtYWlsLmNvbSIsImlhdCI6MTY2Mzc2MTE0NSwiZXhwIjoxNjY0OTcxNjQ1LCJyb2xlIjoiVVNFUiJ9.Nz409isSog1pUqOZ9K-tg4fHECfrMwQDsnO1rMx3oZ5TgL5sxHKtz8NEloMMrx7bkHNE_kGFGsBzopMJ1hTdRA")
        private String refreshToken;

        public static OauthLoginDto.Response of(String accessToken, String refreshToken) {
            return OauthLoginDto.Response.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }

    }
}
