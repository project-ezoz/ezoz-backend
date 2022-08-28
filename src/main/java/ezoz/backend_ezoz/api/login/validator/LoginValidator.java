package ezoz.backend_ezoz.api.login.validator;

import ezoz.backend_ezoz.domain.member.constant.MemberType;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import ezoz.backend_ezoz.global.error.exception.InvalidValueException;
import org.springframework.stereotype.Service;

@Service
public class LoginValidator {

    public void validateMemberType(String memberType) {
        if (!MemberType.isMemberType(memberType)) {
            throw new InvalidValueException(ErrorCode.INVALID_MEMBER_TYPE);
        }

    }
}
