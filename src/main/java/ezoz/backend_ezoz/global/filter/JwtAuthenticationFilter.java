package ezoz.backend_ezoz.global.filter;

import ezoz.backend_ezoz.global.jwt.TokenManager;
import ezoz.backend_ezoz.global.validator.AuthenticationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationValidator authenticationValidator;
    private final TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info(request.getRequestURI() + "에서 요청함");
        String authorizationHeader = request.getHeader("Authorization");
        authenticationValidator.validateAuthorizationHeader(authorizationHeader);

        String token = getToken(authorizationHeader);
        tokenManager.validateToken(token);

        filterChain.doFilter(request, response);
    }

    private String getToken(String authorizationHeader) {
        return authorizationHeader.split(" ")[1];
    }


}
