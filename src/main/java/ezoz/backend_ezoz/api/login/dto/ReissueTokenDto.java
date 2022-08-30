package ezoz.backend_ezoz.api.login.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ReissueTokenDto {

    private String accessToken;

    public static ReissueTokenDto from(String accessToken) {
        return ReissueTokenDto.builder()
                .accessToken(accessToken)
                .build();
    }

}
