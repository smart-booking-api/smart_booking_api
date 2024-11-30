package com.smart.booking.presentation.security.config;

import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.user.service.UserUserService;
import com.smart.booking.presentation.security.filter.JwtFilter;
import com.smart.booking.presentation.security.filter.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final MemberService memberService;
    private final UserUserService userUserService;
    private final PartnerService partnerService;
    private final AuthService authService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // 비밀번호 암호화 방식
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
            // form 로그인 방식 disable
            .formLogin((loginConfig) -> loginConfig.disable())
            // http basic 인증방식 disable
            .httpBasic((httpConfig) -> httpConfig.disable())
            // h2 console 사용을 위해 disable
            .headers((headerConfig) ->
                headerConfig.frameOptions(frameOptionsConfig ->
                    frameOptionsConfig.disable())
            ).authorizeHttpRequests((auth) ->
                auth.requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/", "/join", "/login").permitAll()
                    .requestMatchers("/booking/swagger.html", "/booking/swagger-ui/**", "/booking/v3/**").permitAll()
                    .requestMatchers("/partner").hasAnyRole("PARTNER")
                    .requestMatchers("/reservation/**").hasAnyRole("PARTNER")
                    .requestMatchers("/user/**").hasAnyRole("USER")
                    .anyRequest().authenticated()
            );

        // login filter 적용 전에 jwtFilter 적용
        http.addFilterBefore(new JwtFilter(authService, memberService, userUserService, partnerService), LoginFilter.class);

        // UsernamePasswordAuthenticationFilter 필터 적용시 LoginFilter 를 대신 적용
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), authService), UsernamePasswordAuthenticationFilter.class);

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
