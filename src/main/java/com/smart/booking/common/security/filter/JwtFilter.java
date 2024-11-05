package com.smart.booking.common.security.filter;

import com.smart.booking.common.security.service.JwtService;
import com.smart.booking.common.security.value_object.CustomUserDetails;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter  {
    private final JwtService jwtService;
    private final MemberService memberService;
    private final UserService userService;
    private final PartnerService partnerService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String accessToken = request.getHeader("Authorization");
        String refreshToken = request.getHeader("Refresh-Token");

        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            log.info("access token null");
            filterChain.doFilter(request, response);
            return;
        }

        String token = accessToken.split(" ")[1];

        // 토큰 소멸시간 검증
        if (jwtService.isExpired(token)) {
            log.info("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰에서 userId, role 획득
        String userId = jwtService.getUserId(token);
        String role = jwtService.getRole(token);

        // refresh 3일 이내 만료일 경우 refresh Token 재발급
        if (jwtService.isRefreshExpired(refreshToken.split(" ")[1])) {
            refreshToken = jwtService.createRefreshToken(userId);
            response.setHeader("Refresh-Token", "Bearer " + refreshToken);
            jwtService.updateRefreshToken(userId, refreshToken);
        }

        CustomUserDetails customUserDetails = getCustomUserDetails(userId, role);

        // 스프링 시큐리티 인증토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    private CustomUserDetails getCustomUserDetails(String userId, String role) {
        String password = getPasswordByRole(userId, role);
        return new CustomUserDetails(userId, role, password);
    }

    private String getPasswordByRole(String userId, String role) {
        Member member = memberService.getMemberById(userId);
        return "USER".equals(role)
            ? userService.getUserByMember(member).map(User::getPassword).orElse(null)
            : partnerService.getPartnerByMember(member).map(Partner::getPassword).orElse(null);
    }
}
