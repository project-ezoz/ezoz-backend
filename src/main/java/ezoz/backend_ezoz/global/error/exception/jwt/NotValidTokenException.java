package ezoz.backend_ezoz.global.error.exception.jwt;

import ezoz.backend_ezoz.global.error.exception.BusinessException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;

public class NotValidTokenException extends BusinessException {
    public NotValidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
