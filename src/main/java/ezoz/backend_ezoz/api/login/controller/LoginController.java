package ezoz.backend_ezoz.api.login.controller;

import ezoz.backend_ezoz.api.login.dto.OauthLoginDto;
import ezoz.backend_ezoz.api.login.service.LoginService;
import ezoz.backend_ezoz.api.login.validator.LoginValidator;
import ezoz.backend_ezoz.domain.member.constant.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginValidator loginValidator;
    private final LoginService loginService;


    @PostMapping("/oauth/login")
    public ResponseEntity<OauthLoginDto.Response> oauthLogin(@RequestBody OauthLoginDto.Request oauthLoginRequestDto
            , HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        loginValidator.validateMemberType(oauthLoginRequestDto.getMemberType());

        String accessToken = authorizationHeader.split(" ")[1];
        MemberType memberType = MemberType.from(oauthLoginRequestDto.getMemberType());

        OauthLoginDto.Response response = loginService.loginOauth(accessToken, memberType);

        return ResponseEntity.ok(response);

    }
}
