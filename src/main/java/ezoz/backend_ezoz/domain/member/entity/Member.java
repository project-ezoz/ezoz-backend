package ezoz.backend_ezoz.domain.member.entity;

import ezoz.backend_ezoz.domain.common.BaseEntity;
import ezoz.backend_ezoz.domain.jwt.entity.Token;
import ezoz.backend_ezoz.domain.member.constant.MemberRole;
import ezoz.backend_ezoz.domain.member.constant.MemberType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 20)
    private String memberName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole memberRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType memberType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "token_id")
    private Token token;

    @Builder
    public Member(String email, MemberType memberType, String memberName, MemberRole memberRole) {

        this.email = email;
        this.memberType = memberType;
        this.memberName = memberName;
        this.memberRole = memberRole;
    }

    public void updateRefreshToken(Token token) {
        this.token = token;
    }
}
