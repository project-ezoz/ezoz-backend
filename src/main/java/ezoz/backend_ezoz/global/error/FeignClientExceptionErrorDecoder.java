package ezoz.backend_ezoz.global.error;

import ezoz.backend_ezoz.global.error.exception.FeignClientException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignClientExceptionErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        log.info(response.toString());
        String message = response.reason();
        return new FeignClientException(response.status(), message, response.headers());
    }
}
