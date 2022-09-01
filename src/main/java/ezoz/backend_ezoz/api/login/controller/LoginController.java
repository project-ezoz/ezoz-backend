package ezoz.backend_ezoz.api.login.controller;

import ezoz.backend_ezoz.api.login.dto.OauthLoginDto;
import ezoz.backend_ezoz.api.login.dto.ReissueTokenDto;
import ezoz.backend_ezoz.api.login.service.LoginService;
import ezoz.backend_ezoz.api.login.validator.LoginValidator;
import ezoz.backend_ezoz.domain.member.constant.MemberType;
import ezoz.backend_ezoz.global.validator.AuthenticationValidator;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final AuthenticationValidator authenticationValidator;
    private final LoginValidator loginValidator;
    private final LoginService loginService;

    @PostMapping("/oauth/sign-up")
    public ResponseEntity<Long> oauthSignUp(@RequestBody OauthLoginDto.Request oauthLoginRequestDto
            , HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        authenticationValidator.validateAuthorizationHeader(authorizationHeader);
        loginValidator.validateMemberType(oauthLoginRequestDto.getMemberType());

        MemberType memberType = MemberType.from(oauthLoginRequestDto.getMemberType());

        Long memberId = loginService.signUpOauth(authorizationHeader, memberType);

        return ResponseEntity.ok(memberId);

    }

    @ApiOperation(value = "소셜 로그인 API", notes = "카카오 액세스 토큰을 통해 서버의 JWT 토큰을 발급받는다.")
    @PostMapping("/oauth/login")
    public ResponseEntity<OauthLoginDto.Response> oauthLogin(@RequestBody OauthLoginDto.Request oauthLoginRequestDto
            , HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        authenticationValidator.validateAuthorizationHeader(authorizationHeader);
        loginValidator.validateMemberType(oauthLoginRequestDto.getMemberType());

        MemberType memberType = MemberType.from(oauthLoginRequestDto.getMemberType());

        OauthLoginDto.Response response = loginService.loginOauth(authorizationHeader, memberType);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/reissue")
    public ResponseEntity<ReissueTokenDto> reissueAccessToken(HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String refreshToken = authorizationHeader.split(" ")[1];
        ReissueTokenDto reissueTokenDto = loginService.reissueAccessToken(refreshToken);

        log.info("컨트롤러에서 리이슈토큰 나가기 전");
        return ResponseEntity.ok(reissueTokenDto);
    }
}
