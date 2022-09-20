package ezoz.backend_ezoz.web.googletoken;

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
public class GoogleTokenController {

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.callback-uri}")
    private String callbackUri;

    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
    private final int CONTENT_LENGTH = 0;
    private final String GRANT_TYPE = "authorization_code";

    private final GoogleTokenClient googleTokenClient;

    @GetMapping("/google/login")
    public String login() {
        return "googleLoginForm";
    }

    @GetMapping("/auth/google/callback")
    public @ResponseBody
    ResponseEntity<GoogleResponseDto> loginCallback(String code) {
        log.info("구글 리다이렉트 컨트롤러 접속");
        GoogleResponseDto googleResponseDto = googleTokenClient
                .getGoogleToken(CONTENT_TYPE, clientId, clientSecret, code, GRANT_TYPE, callbackUri);

        return ResponseEntity.ok(googleResponseDto);
    }
}
