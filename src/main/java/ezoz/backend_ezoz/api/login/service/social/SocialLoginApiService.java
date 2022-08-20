package ezoz.backend_ezoz.api.login.service.social;

import ezoz.backend_ezoz.api.login.dto.OAuthAttributes;

public interface SocialLoginApiService {

    OAuthAttributes getUserInfo(String accessToken);

}
