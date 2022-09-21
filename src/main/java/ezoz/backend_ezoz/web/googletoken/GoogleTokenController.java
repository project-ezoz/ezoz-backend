package ezoz.backend_ezoz.web.googletoken;

import io.swagger.annotations.ApiModel;
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


    @ApiOperation(value = "구글 로그인 페이지")
    @GetMapping("/google/login")
    public String login() {
        return "googleLoginForm";
    }

    @ApiIgnore
    @GetMapping("/auth/google/callback")
    public @ResponseBody
    ResponseEntity<GoogleResponseDto> loginCallback(String code) {

        GoogleResponseDto googleResponseDto = googleTokenClient
                .getGoogleToken(CONTENT_TYPE, clientId, clientSecret, code, GRANT_TYPE, callbackUri);

        return ResponseEntity.ok(googleResponseDto);
    }
}
