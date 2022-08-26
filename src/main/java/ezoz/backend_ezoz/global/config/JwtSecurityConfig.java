package ezoz.backend_ezoz.global.config;

import ezoz.backend_ezoz.domain.jwt.service.TokenManager;
import ezoz.backend_ezoz.global.filter.JwtAuthenticationFilter;
import ezoz.backend_ezoz.global.validator.AuthenticationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

//@Component
//@RequiredArgsConstructor
//public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//    private final AuthenticationValidator authenticationValidator;
//    private final TokenManager tokenManager;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(new JwtAuthenticationFilter(authenticationValidator, tokenManager)
//                , UsernamePasswordAuthenticationFilter.class);
//    }
//}
