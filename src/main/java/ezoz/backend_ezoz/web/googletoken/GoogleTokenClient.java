package ezoz.backend_ezoz.web.googletoken;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://oauth2.googleapis.com", name = "googleTokenFeignClient")
public interface GoogleTokenClient {
    @PostMapping("/token")
    GoogleResponseDto getGoogleToken(
            @RequestHeader("Content-Type") String contentType,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("code") String code,
            @RequestParam("grant_type") String grantType,
            @RequestParam("redirect_uri") String redirectUri
    );
}
