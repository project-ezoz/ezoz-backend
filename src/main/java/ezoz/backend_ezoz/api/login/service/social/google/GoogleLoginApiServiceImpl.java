package ezoz.backend_ezoz.api.login.service.social.google;

import ezoz.backend_ezoz.api.login.dto.GoogleUserInfo;
import ezoz.backend_ezoz.api.login.dto.OAuthAttributes;
import ezoz.backend_ezoz.api.login.service.social.SocialLoginApiService;
import ezoz.backend_ezoz.domain.member.constant.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoogleLoginApiServiceImpl implements SocialLoginApiService {

    private final GoogleFeignClient googleFeignClient;

    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";


    @Override
    public OAuthAttributes getUserInfo(String accessToken) {
        GoogleUserInfo googleUserInfo = googleFeignClient.getGoogleUserInfo(CONTENT_TYPE, accessToken);

        return OAuthAttributes.builder()
                .email(googleUserInfo.getEmail())
                .memberName(googleUserInfo.getName())
                .memberType(MemberType.GOOGLE)
                .build();
    }
}
