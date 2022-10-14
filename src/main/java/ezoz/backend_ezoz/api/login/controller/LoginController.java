package ezoz.backend_ezoz.api.login.controller;

import ezoz.backend_ezoz.api.login.dto.OauthLoginDto;
import ezoz.backend_ezoz.api.login.dto.ReissueTokenDto;
import ezoz.backend_ezoz.api.login.service.LoginService;
import ezoz.backend_ezoz.api.login.validator.LoginValidator;
import ezoz.backend_ezoz.domain.member.constant.MemberType;
import ezoz.backend_ezoz.global.validator.AuthenticationValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@ApiIgnore
public class LoginController {

    private final AuthenticationValidator authenticationValidator;
    private final LoginValidator loginValidator;
    private final LoginService loginService;

    @ApiIgnore
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

    @ApiOperation(value = "토큰 재발급 API", notes = "서버에서 발급받은 리프레쉬 토큰을 통해 액세스 토큰을 발급받는다.")
    @GetMapping("/reissue")
    public ResponseEntity<ReissueTokenDto> reissueAccessToken(HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String refreshToken = authorizationHeader.split(" ")[1];
        ReissueTokenDto reissueTokenDto = loginService.reissueAccessToken(refreshToken);

        return ResponseEntity.ok(reissueTokenDto);
    }
}
