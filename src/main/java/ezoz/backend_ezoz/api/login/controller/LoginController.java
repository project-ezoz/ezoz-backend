package ezoz.backend_ezoz.api.login.controller;

import ezoz.backend_ezoz.api.login.dto.OauthLoginDto;
import ezoz.backend_ezoz.api.login.dto.ReissueTokenDto;
import ezoz.backend_ezoz.api.login.service.LoginService;
import ezoz.backend_ezoz.api.login.validator.LoginValidator;
import ezoz.backend_ezoz.domain.member.constant.MemberType;
import ezoz.backend_ezoz.global.validator.AuthenticationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

        return ResponseEntity.ok(reissueTokenDto);
    }
}
