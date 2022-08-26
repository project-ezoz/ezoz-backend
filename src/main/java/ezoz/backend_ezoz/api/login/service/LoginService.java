package ezoz.backend_ezoz.api.login.service;

import ezoz.backend_ezoz.api.login.dto.OAuthAttributes;
import ezoz.backend_ezoz.api.login.dto.OauthLoginDto;
import ezoz.backend_ezoz.api.login.service.social.SocialLoginApiService;
import ezoz.backend_ezoz.api.login.service.social.SocialLoginApiServiceFactory;
import ezoz.backend_ezoz.domain.jwt.entity.Token;
import ezoz.backend_ezoz.domain.jwt.service.TokenManager;
import ezoz.backend_ezoz.domain.member.constant.MemberRole;
import ezoz.backend_ezoz.domain.member.constant.MemberType;
import ezoz.backend_ezoz.domain.member.entity.Member;
import ezoz.backend_ezoz.domain.member.service.MemberService;
import ezoz.backend_ezoz.global.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final MemberService memberService;
    private final TokenManager tokenManager;

    @Transactional
    public OauthLoginDto.Response loginOauth(String authorizationHeader, MemberType memberType) {

        OAuthAttributes socialUserInfo = getSocialUserInfo(authorizationHeader, memberType);

        Member member = memberService.findByEmail(socialUserInfo.getEmail())
                .orElseGet(() -> registerMember(socialUserInfo, memberType));

        Token token = tokenManager.createToken(socialUserInfo.getEmail(), member.getMemberRole());

        refreshMemberToken(member, token.getRefreshToken(), token.getRefreshTokenExpireTime());

        return OauthLoginDto.Response.from(token);

    }

    private OAuthAttributes getSocialUserInfo(String accessToken, MemberType memberType) {

        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);

        return socialLoginApiService.getUserInfo(accessToken);
    }

    private Member registerMember(OAuthAttributes oAuthAttributes, MemberType memberType) {

        Member member = oAuthAttributes.toMemberEntity(memberType, MemberRole.USER);
        return memberService.registerMember(member);

    }

    private void refreshMemberToken(Member member, String refreshToken, Date refreshTokenExpireTime) {
        LocalDateTime convertToLocalDateTime = DateTimeUtils.convertToLocalDateTime(refreshTokenExpireTime);
        member.updateRefreshToken(refreshToken, convertToLocalDateTime);
    }


}
