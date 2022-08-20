package ezoz.backend_ezoz.web.kakaotoken;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KakaoTokenRequestDto {

    private String Content_Type;

    private String grant_type;

    private String client_id;

    private String redirect_uri;

    private String code;

    private String client_secret;

    public static KakaoTokenRequestDto of(String client_id, String code, String client_secret,
                                          String Content_Type, String grant_type, String redirectUrl) {
        return KakaoTokenRequestDto.builder()
                .Content_Type(Content_Type)
                .grant_type(grant_type)
                .client_id(client_id)
                .redirect_uri(redirectUrl)
                .code(code)
                .client_secret(client_secret)
                .build();
    }
}
