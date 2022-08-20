package ezoz.backend_ezoz.web.kakaotoken;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "https://kauth.kakao.com", name = "kakaoTokenFeignClient")
public interface KakaoTokenClient {
    @PostMapping("/oauth/token")
    ResponseEntity<KakaoTokenResponseDto> getKakaoToken(@SpringQueryMap KakaoTokenRequestDto kakaoTokenRequestDto);
}
