package ezoz.backend_ezoz.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ezoz.backend_ezoz.global.jwt.TokenManager;
import ezoz.backend_ezoz.global.filter.ExceptionHandlerFilter;
import ezoz.backend_ezoz.global.filter.JwtAuthenticationFilter;
import ezoz.backend_ezoz.global.validator.AuthenticationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationValidator authenticationValidator;
    private final TokenManager tokenManager;
    private final ObjectMapper objectMapper;
    private final String[] WHITE_LIST =
            {"/", "/kakao/login", "/api/oauth/login", "/auth/kakao/callback", "/google/login", "/auth/google/callback",
                    "/favicon.ico", "/error",
                    "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) ->
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .accessDeniedHandler((request, response, accessDeniedException) ->
                        response.sendError(HttpServletResponse.SC_FORBIDDEN))
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(authenticationValidator, tokenManager),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new ExceptionHandlerFilter(objectMapper), JwtAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(WHITE_LIST);
    }

}
