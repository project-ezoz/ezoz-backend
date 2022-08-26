package ezoz.backend_ezoz.api.login.dto;

import ezoz.backend_ezoz.domain.member.constant.MemberRole;
import ezoz.backend_ezoz.domain.member.constant.MemberType;
import ezoz.backend_ezoz.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthAttributes {
    private String memberName;
    private String email;
    private MemberType memberType;

    public Member toMemberEntity(MemberType memberType, MemberRole memberRole) {
        return Member.builder()
                .email(email)
                .memberName(memberName)
                .memberType(memberType)
                .memberRole(MemberRole.USER)
                .build();
    }

}
