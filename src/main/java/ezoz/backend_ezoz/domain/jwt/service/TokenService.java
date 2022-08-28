package ezoz.backend_ezoz.domain.jwt.service;

import ezoz.backend_ezoz.domain.jwt.entity.Token;
import ezoz.backend_ezoz.domain.jwt.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public Long saveToken(Token token) {
        return tokenRepository.save(token).getTokenId();
    }
}
