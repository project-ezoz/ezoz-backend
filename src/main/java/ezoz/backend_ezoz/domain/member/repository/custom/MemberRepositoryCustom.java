package ezoz.backend_ezoz.domain.member.repository.custom;

import ezoz.backend_ezoz.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findByEmailFetchToken(String email);
}
