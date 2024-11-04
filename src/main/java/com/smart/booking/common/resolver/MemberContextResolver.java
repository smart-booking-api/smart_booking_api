package com.smart.booking.common.resolver;

import com.smart.booking.common.dto.MemberContext;
import com.smart.booking.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class MemberContextResolver implements HandlerMethodArgumentResolver {
    private final MemberService memberService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(MemberContext.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory) throws Exception {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var memberDto = memberService.getMemberById(auth.getName());

        return MemberContext.builder()
            .memberType(memberDto.getType())
            .memberId(memberDto.getId())
            .build();
    }
}
