package ezoz.backend_ezoz.global.error.exception;

public class EntityAlreadyExistException extends BusinessException{

    public EntityAlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
