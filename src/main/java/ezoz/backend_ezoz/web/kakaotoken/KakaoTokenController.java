package ezoz.backend_ezoz.web.kakaotoken;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaoTokenController {

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    private final KakaoTokenClient kakaoTokenClient;

    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
    private final String GRANT_TYPE = "authorization_code";

    @ApiOperation(value = "구글 로그인 페이지")
    @GetMapping("/kakao/login")
    public String login() {
        return "loginForm";
    }

    @ApiIgnore
    @GetMapping("/auth/kakao/callback")
    public @ResponseBody
    ResponseEntity<KakaoTokenResponseDto> loginCallback(String code) {

        KakaoTokenResponseDto kakaoToken = kakaoTokenClient.getKakaoToken(CONTENT_TYPE,
                GRANT_TYPE, clientId, redirectUri, code, clientSecret);

        return ResponseEntity.ok(kakaoToken);

    }

}
