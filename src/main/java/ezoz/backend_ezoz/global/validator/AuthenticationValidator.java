package ezoz.backend_ezoz.global.validator;

import ezoz.backend_ezoz.domain.jwt.constant.GrantType;
import ezoz.backend_ezoz.global.error.exception.AuthenticationException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthenticationValidator {

    public void validateAuthorizationHeader(String authorizationHeader) {

        // Authorization 헤더 있는지 확인
        if (!StringUtils.hasText(authorizationHeader)) {
            throw new AuthenticationException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }

        // 2. authorization Bearer 체크
        String[] authorizations = authorizationHeader.split(" ");
        if (!GrantType.BEARRER.getType().equals(authorizations[0])) {
            throw new AuthenticationException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);
        }
    }
}
