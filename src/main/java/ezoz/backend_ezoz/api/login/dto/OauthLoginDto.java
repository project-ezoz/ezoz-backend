package ezoz.backend_ezoz.api.login.dto;

import ezoz.backend_ezoz.domain.jwt.entity.Token;
import lombok.*;

public class OauthLoginDto {
    @Getter
    @Setter
    public static class Request {
        private String memberType;
    }

    @Getter @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private String accessToken;

        private String refreshToken;

        public static OauthLoginDto.Response from(Token token) {
            return OauthLoginDto.Response.builder()
                    .accessToken(token.getAccessToken())
                    .refreshToken(token.getRefreshToken())
                    .build();
        }

    }
}
