package ezoz.backend_ezoz.domain.member.entity;

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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 20)
    private String memberName;

    @Column(nullable = false)
    private MemberRole memberRole;

    @Column(nullable = false)
    private MemberType memberType;

    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    @Builder
    public Member(String email, MemberType memberType, String memberName, MemberRole memberRole, String refreshToken, LocalDateTime tokenExpirationTime) {

        this.email = email;
        this.memberType = memberType;
        this.memberName = memberName;
        this.memberRole = memberRole;
        this.refreshToken = refreshToken;
        this.tokenExpirationTime = tokenExpirationTime;

    }

    public void updateRefreshToken(String refreshToken, LocalDateTime tokenExpirationTime) {
        this.refreshToken = refreshToken;
        this.tokenExpirationTime = tokenExpirationTime;
    }
}
