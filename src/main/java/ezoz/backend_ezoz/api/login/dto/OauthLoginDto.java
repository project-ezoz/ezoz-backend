package ezoz.backend_ezoz.api.login.dto;

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

        public static OauthLoginDto.Response of(String accessToken, String refreshToken) {
            return OauthLoginDto.Response.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }

    }
}
