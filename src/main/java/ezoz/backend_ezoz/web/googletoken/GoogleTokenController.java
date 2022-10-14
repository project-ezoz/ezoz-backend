package ezoz.backend_ezoz.web.googletoken;

import ezoz.backend_ezoz.api.login.dto.OauthLoginDto;
import ezoz.backend_ezoz.api.login.service.LoginService;
import ezoz.backend_ezoz.domain.member.constant.MemberType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@Controller
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@ApiIgnore
public class GoogleTokenController {

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.callback-uri}")
    private String callbackUri;

    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
    private final String GRANT_TYPE = "authorization_code";

    private final GoogleTokenClient googleTokenClient;
    private final LoginService loginService;



    @ApiIgnore
    @GetMapping("/google/login")
    public String login() {
        return "googleLoginForm";
    }

    @ApiIgnore
    @GetMapping("/auth/google/callback")
    public @ResponseBody
    ResponseEntity<String> getCode(String code) {
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "서버 토큰 발급 API", notes = "소셜 액세스 토큰을 통해 서버의 JWT 토큰을 발급받는다.", tags = "Account")
    @GetMapping("/auth/google")
    public @ResponseBody
    ResponseEntity<OauthLoginDto.Response> googleLogin(String code) {

        GoogleResponseDto googleResponseDto = googleTokenClient
                .getGoogleToken(clientId, clientSecret, code, GRANT_TYPE, callbackUri);

        OauthLoginDto.Response response = loginService.loginOauth(googleResponseDto.getAccess_token(), MemberType.GOOGLE);

        return ResponseEntity.ok(response);
    }
}
