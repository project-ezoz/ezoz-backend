package ezoz.backend_ezoz.api.login.service;

import ezoz.backend_ezoz.api.login.dto.OAuthAttributes;
import ezoz.backend_ezoz.api.login.dto.OauthLoginDto;
import ezoz.backend_ezoz.api.login.dto.ReissueTokenDto;
import ezoz.backend_ezoz.api.login.service.social.SocialLoginApiService;
import ezoz.backend_ezoz.api.login.service.social.SocialLoginApiServiceFactory;
import ezoz.backend_ezoz.domain.jwt.entity.Token;
import ezoz.backend_ezoz.global.jwt.TokenManager;
import ezoz.backend_ezoz.domain.jwt.service.TokenService;
import ezoz.backend_ezoz.domain.member.constant.MemberRole;
import ezoz.backend_ezoz.domain.member.constant.MemberType;
import ezoz.backend_ezoz.domain.member.entity.Member;
import ezoz.backend_ezoz.domain.member.service.MemberService;
import ezoz.backend_ezoz.global.error.exception.EntityAlreadyExistException;
import ezoz.backend_ezoz.global.error.exception.EntityNotFoundException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import ezoz.backend_ezoz.global.error.exception.jwt.NotValidTokenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final MemberService memberService;
    private final TokenService tokenService;
    private final TokenManager tokenManager;

    @Transactional
    public Long signUpOauth(String authorizationHeader, MemberType memberType) {
        OAuthAttributes socialUserInfo = getSocialUserInfo(authorizationHeader, memberType);

        if (AlreadyRegister(socialUserInfo.getEmail())) {
            throw new EntityAlreadyExistException(ErrorCode.ALREADY_REGISTERED_MEMBER);
        }

        Member member = registerMember(socialUserInfo, memberType);

        return member.getMemberId();

    }

    private boolean AlreadyRegister(String email) {
        Optional<Member> optionalMember = memberService.findByEmail(email);
        return optionalMember.isPresent();
    }

    @Transactional
    public OauthLoginDto.Response loginOauth(String socialAccessToken, MemberType memberType) {

        String addTokenTypeAccessToken = addTokenTypeAccessToken(socialAccessToken);

        OAuthAttributes socialUserInfo = getSocialUserInfo(addTokenTypeAccessToken, memberType);

        Member member = memberService.findByEmail(socialUserInfo.getEmail())
                .orElseGet(() -> registerMember(socialUserInfo, memberType));

        String accessToken = tokenManager.createAccessToken(socialUserInfo.getEmail(), member.getMemberRole());
        Token refreshToken = tokenManager.createRefreshToken(socialUserInfo.getEmail(), member.getMemberRole());
        tokenService.saveToken(refreshToken);

        refreshMemberToken(member, refreshToken);

        return OauthLoginDto.Response.of(accessToken, refreshToken.getRefreshToken());

    }

    private String addTokenTypeAccessToken(String accessToken) {
        return "Bearer " + accessToken;
    }

    private OAuthAttributes getSocialUserInfo(String accessToken, MemberType memberType) {

        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);

        return socialLoginApiService.getUserInfo(accessToken);
    }

    private Member registerMember(OAuthAttributes oAuthAttributes, MemberType memberType) {

        Member member = oAuthAttributes.toMemberEntity(memberType, MemberRole.USER);

        return memberService.registerMember(member);

    }

    private void refreshMemberToken(Member member, Token token) {
        member.updateRefreshToken(token);
    }

    public ReissueTokenDto reissueAccessToken(String refreshToken) {
        String email = tokenManager.getEmail(refreshToken);

        Member member = memberService.findByEmailFetchToken(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));

        Token beforeToken = member.getToken();
        if (isDifferentToken(refreshToken, beforeToken.getRefreshToken())) {
            throw new NotValidTokenException(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
        }

        String accessToken = tokenManager.createAccessToken(email, member.getMemberRole());

        log.info("리이슈 service 함수 끝남");
        return ReissueTokenDto.from(accessToken);

    }

    private boolean isDifferentToken(String clientToken, String serverToken) {
        return !clientToken.equals(serverToken);
    }

}
