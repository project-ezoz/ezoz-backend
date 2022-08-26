package ezoz.backend_ezoz.api.login.service.social.kakao;

import ezoz.backend_ezoz.api.login.dto.KakaoUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://kapi.kakao.com", name = "kakaoFeignClient")
public interface KakaoFeignClient {

    @GetMapping(value = "/v2/user/me", consumes = "application/json")
    KakaoUserInfo getKakaoUserInfo(
            @RequestHeader("Content-type") String contentType,
            @RequestHeader("Authorization") String Authorization
    );

}
