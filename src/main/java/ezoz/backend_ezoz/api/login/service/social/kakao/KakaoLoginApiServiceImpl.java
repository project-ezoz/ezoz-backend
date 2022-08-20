package ezoz.backend_ezoz.api.login.service.social.kakao;

import ezoz.backend_ezoz.api.login.dto.KakaoUserInfo;
import ezoz.backend_ezoz.api.login.dto.OAuthAttributes;
import ezoz.backend_ezoz.api.login.service.social.SocialLoginApiService;
import ezoz.backend_ezoz.domain.member.constant.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoLoginApiServiceImpl implements SocialLoginApiService {

    private final KakaoFeignClient kakaoFeignClient;
    private final String PROPERTY_NICKNAME = "nickname";

    @Override
    public OAuthAttributes getUserInfo(String accessToken) {
        KakaoUserInfo kakaoUserInfo = kakaoFeignClient.getKakaoUserInfo(accessToken);

        String email = kakaoUserInfo.getKakaoAccount().getEmail();

        // 서비스에 연결하는 시점의 카카오계정 프로필을 복사하여 기본으로 제공 => 2022-05-12 실시간 프로필만 제공하는걸로 정책 변경
        Map<String, String> properties = kakaoUserInfo.getProperties();
        String nickname = properties.get(PROPERTY_NICKNAME);

        nickname = kakaoUserInfo.getKakaoAccount().getProfile().getNickname(); // 실시간 프로필에 있는 닉네임 조회

        return OAuthAttributes.builder()
                .email(StringUtils.hasText(email) ? email : kakaoUserInfo.getId() )
                .memberName(nickname)
                .memberType(MemberType.KAKAO)
                .build();

    }
}
