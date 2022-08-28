package ezoz.backend_ezoz.domain.member.repository;

import ezoz.backend_ezoz.domain.member.entity.Member;
import ezoz.backend_ezoz.domain.member.repository.custom.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);
}
