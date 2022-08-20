package ezoz.backend_ezoz.domain.member.service;

import ezoz.backend_ezoz.domain.member.entity.Member;
import ezoz.backend_ezoz.domain.member.repository.MemberRepository;
import ezoz.backend_ezoz.global.error.exception.EntityNotFoundException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }

}
