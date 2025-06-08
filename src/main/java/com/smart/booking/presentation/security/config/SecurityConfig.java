package com.smart.booking.presentation.security.config;

import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.user.service.UserService;
import com.smart.booking.presentation.security.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final MemberService memberService;
    private final UserService userUserService;
    private final PartnerService partnerService;
    private final AuthService authService;
    private final RequestMatcher get = request -> {
        String uri = request.getRequestURI();
        return uri.matches("^/api/v\\d+/auth/.*");
    };

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // 비밀번호 암호화 방식
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            // form 로그인 방식 disable
            .formLogin(AbstractHttpConfigurer::disable)
            // http basic 인증방식 disable
            .httpBasic(AbstractHttpConfigurer::disable)
            // h2 console 사용을 위해 disable
            .headers((headerConfig) ->
                headerConfig.frameOptions(FrameOptionsConfig::disable)
            ).authorizeHttpRequests((auth) -> {
                    auth.requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers(request -> request.getRequestURI().matches("^/api/v\\d+/auth/.*")).permitAll()
                        .requestMatchers("/api-docs/**", "/swagger-ui/**", "/actuator/**").permitAll()
                        .requestMatchers("/api/partner/**").hasAnyRole("PARTNER")
                        .requestMatchers("/api/user/**").hasAnyRole("USER")
                        .anyRequest().authenticated();
                }
            );

        // login filter 적용 전에 jwtFilter 적용
        http.addFilterBefore(new JwtFilter(authService, memberService, userUserService, partnerService), UsernamePasswordAuthenticationFilter.class);

        // jwt 인증인가를 위한 세션 설정
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling((exceptionConfig) ->
                exceptionConfig
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)
            );

        return http.build();
    }

}
