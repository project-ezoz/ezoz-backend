package ezoz.backend_ezoz.web.kakaotoken;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class KakaoTokenResponseDto {
    @NotNull
    private String token_type;

    @NotNull
    private String access_token;

    @NotBlank
    private Integer expires_in;

    @NotNull
    private String refresh_token;

    @NotBlank
    private Integer refresh_token_expires_in;

    @NotBlank
    private String scope;
}
