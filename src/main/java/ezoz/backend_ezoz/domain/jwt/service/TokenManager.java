package ezoz.backend_ezoz.domain.jwt.service;

import ezoz.backend_ezoz.domain.jwt.constant.GrantType;
import ezoz.backend_ezoz.domain.jwt.constant.TokenType;
import ezoz.backend_ezoz.domain.jwt.entity.Token;
import ezoz.backend_ezoz.global.error.exception.jwt.NotValidTokenException;
import ezoz.backend_ezoz.domain.member.constant.MemberRole;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@Log4j2
public class TokenManager {

    private final String accessTokenExpirationTime;

    private final String refreshTokenExpirationTime;

    private final Key key;

    public TokenManager(@Value("${token.access-token-expiration-time}") String accessTokenExpirationTime,
                        @Value("${token.refresh-token-expiration-time}") String refreshTokenExpirationTime,
                        @Value("${token.secret}") String secret) {

        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
        byte[] decode = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(decode);

    }

    public Token createToken(String email, MemberRole memberRole) {

        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExpireTime = createRefreshTokenExpireTime();

        String accessToken = createAccessToken(email, memberRole, accessTokenExpireTime);
        String refreshToken = createRefreshToken(email, refreshTokenExpireTime);

        return Token.builder().
                grantType(GrantType.BEARRER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(refreshTokenExpireTime)
                .build();

    }

    public Date createAccessTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    public Date createRefreshTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    public String createAccessToken(String email, MemberRole role, Date expirationTime) {

        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name())                // 토큰 제목
                .setAudience(email)                                 // 토큰 대상자
                .setIssuedAt(new Date())                            // 토큰 발급 시간
                .setExpiration(expirationTime)                      // 토큰 만료 시간
                .claim("role", role)                          // 유저 role
                .signWith(key, SignatureAlgorithm.HS512)
                .setHeaderParam("typ", "JWT")
                .compact();

        return accessToken;
    }

    public String createRefreshToken(String email, Date expirationTime) {
        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name())               // 토큰 제목
                .setAudience(email)                                 // 토큰 대상자
                .setIssuedAt(new Date())                            // 토큰 발급 시간
                .setExpiration(expirationTime)                      // 토큰 만료 시간
                .signWith(key, SignatureAlgorithm.HS512)
                .setHeaderParam("typ", "JWT")
                .compact();

        return refreshToken;
    }

    public String getMemberUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody().getAudience();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotValidTokenException(ErrorCode.NOT_VALID_TOKEN);
        }
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (MalformedJwtException | SecurityException e) {
            log.error("잘못된 jwt token");
            throw new NotValidTokenException(ErrorCode.INVALID_TOKEN_SIGNATURE);
        } catch (ExpiredJwtException e) {
            log.error("만료된 jwt token");
            throw new NotValidTokenException(ErrorCode.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error("지원하지 않는 jwt token");
            throw new NotValidTokenException(ErrorCode.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            log.error("잘못된 jwt token");
            throw new NotValidTokenException(ErrorCode.NOT_VALID_TOKEN);
        }
    }

}
