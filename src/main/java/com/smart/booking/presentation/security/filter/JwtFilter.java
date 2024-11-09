package com.smart.booking.presentation.security.filter;

import com.smart.booking.domain.auth.service.AuthService;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.user.service.UserCommonService;
import com.smart.booking.presentation.security.value_object.CustomUserDetails;
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
public class JwtFilter extends OncePerRequestFilter {

    private final AuthService authService;
    private final MemberService memberService;
    private final UserCommonService userCommonService;
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
        if (authService.isExpiredToken(token)) {
            log.info("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰에서 userId 획득
        String userId = authService.getUserIdFromToken(token);

        // refresh 3일 이내 만료일 경우 refresh Token 재발급
        if (authService.isExpiredRefreshToken(refreshToken.split(" ")[1])) {
            refreshToken = authService.createRefreshToken(userId);
            response.setHeader("Refresh-Token", "Bearer " + refreshToken);
            authService.updateRefreshToken(userId, refreshToken);
        }

        CustomUserDetails customUserDetails = getCustomUserDetails(userId);

        // 스프링 시큐리티 인증토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    private CustomUserDetails getCustomUserDetails(String userId) {
        Member member = memberService.getMemberById(userId);
        return new CustomUserDetails(userId, member.getType().getKey(), getPasswordByRole(member));
    }

    private String getPasswordByRole(Member member) {
        return MemberType.USER.equals(member.getType())
            ? "" : partnerService.getPartnerByMember(member).map(Partner::getPassword).orElse(null);
    }
}
