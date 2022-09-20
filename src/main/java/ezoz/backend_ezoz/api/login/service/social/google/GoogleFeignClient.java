package ezoz.backend_ezoz.api.login.service.social.google;

import ezoz.backend_ezoz.api.login.dto.GoogleUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://www.googleapis.com/oauth2", name = "googleFeignClient")
public interface GoogleFeignClient {
    @GetMapping(value = "v1/userinfo", consumes = "application/json")
    GoogleUserInfo getGoogleUserInfo(
            @RequestHeader("Content-type") String contentType,
            @RequestHeader("Authorization") String Authorization
    );
}
