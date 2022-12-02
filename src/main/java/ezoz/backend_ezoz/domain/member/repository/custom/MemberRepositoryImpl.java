package ezoz.backend_ezoz.domain.member.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ezoz.backend_ezoz.domain.jwt.entity.QToken;
import ezoz.backend_ezoz.domain.member.entity.Member;
import ezoz.backend_ezoz.domain.member.entity.QMember;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static ezoz.backend_ezoz.domain.jwt.entity.QToken.token;
import static ezoz.backend_ezoz.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Member> findByEmailFetchToken(String email) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .leftJoin(member.token, token).fetchJoin()
                .where(emailEquals(email))
                .fetchOne());
    }

    private BooleanExpression emailEquals(String email) {
        return member.email.eq(email);
    }
}
