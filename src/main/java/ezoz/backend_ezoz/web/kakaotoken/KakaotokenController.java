package ezoz.backend_ezoz.web.kakaotoken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaotokenController {

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.redirect-url}")
    private String redirectUri;

    private final KakaoTokenClient kakaoTokenClient;

    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
    private final String GRANT_TYPE = "authorization_code";

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody
    ResponseEntity<KakaoTokenResponseDto> loginCallback(String code) {
        KakaoTokenResponseDto kakaoToken = kakaoTokenClient.getKakaoToken(CONTENT_TYPE,
                GRANT_TYPE, clientId, redirectUri, code, clientSecret);

        return ResponseEntity.ok(kakaoToken);

    }

}
