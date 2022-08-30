package ezoz.backend_ezoz.api.login.dto;


import lombok.Builder;

@Builder
public class ReissueTokenDto {

    private String accessToken;

    public static ReissueTokenDto from(String accessToken) {
        return ReissueTokenDto.builder()
                .accessToken(accessToken)
                .build();
    }

}
