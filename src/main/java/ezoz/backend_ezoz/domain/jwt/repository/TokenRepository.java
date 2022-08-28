package ezoz.backend_ezoz.domain.jwt.repository;

import ezoz.backend_ezoz.domain.jwt.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
