package ezoz.backend_ezoz.web.kakaotoken;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class KakaoTokenResponseDto {
    @NotNull
    private String token_type;

    @NotNull
    private String access_token;

    @NotBlank
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Integer expires_in;

    @NotNull
    private String refresh_token;

    @NotBlank
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Integer refresh_token_expires_in;

    @NotBlank
    private String scope;
}
