package ezoz.backend_ezoz.web.kakaotoken;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class KakaotokenController {

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @Value("${kakao.client.id}")
    private String clientId;

    private final KakaoTokenClient kakaoTokenClient;

    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
    private final String GRANT_TYPE = "authorization_code";
    private final String REDIRECT_URI = "http://localhost:8080/auth/kakao/callback";

    @GetMapping("/login")
    public String login(){
        return "loginForm";
    }

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody
    String loginCallback(String code){

        KakaoTokenRequestDto kakaoTokenRequestDto =
                KakaoTokenRequestDto.of(code, clientId, clientSecret, CONTENT_TYPE, GRANT_TYPE, REDIRECT_URI);

        ResponseEntity<KakaoTokenResponseDto> kakaoToken = kakaoTokenClient.getKakaoToken(kakaoTokenRequestDto);

        return "kakao token : " + kakaoToken;

    }

}
