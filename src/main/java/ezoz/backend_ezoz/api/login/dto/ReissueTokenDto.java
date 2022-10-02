package ezoz.backend_ezoz.api.login.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReissueTokenDto {

    @ApiModelProperty(value = "재발급 액세스 토큰", required = true, example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBQ0NFU1MiLCJhdWQiOiJlem96OTA1NjNAZ21haWwuY29tIiwiaWF0IjoxNjYzNzYxMTQ1LCJleHAiOjE2NjM3NjIwNDUsInJvbGUiOiJVU0VSIn0.daqncRaD45I7rsVzWzwPpcGLQwrEBLwkhlLzX5CFzNPDnT-QRqpslrtGMPRmrUPoXn46umOTBGbvGYNTjwiEZw")
    private String accessToken;

    public static ReissueTokenDto from(String accessToken) {
        return ReissueTokenDto.builder()
                .accessToken(accessToken)
                .build();
    }

}
