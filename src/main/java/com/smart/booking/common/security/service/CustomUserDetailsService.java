package com.smart.booking.common.security.service;

import com.smart.booking.common.security.value_object.CustomUserDetails;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.repository.UserRepository;
import com.smart.booking.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final PartnerService partnerService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userService.getUserByEmailId(userId).orElse(null);
        if (isValidUser(user)) {
            return new CustomUserDetails(user.getMember().getId(), user.getMember().getType().getKey(), user.getPassword());
        }

        Partner partner = partnerService.getPartnerByLoginId(userId).orElse(null);
        if (isValidPartner(partner)) {
            return new CustomUserDetails(partner.getMember().getId(), partner.getMember().getType().getKey(), partner.getPassword());
        }

        return null;
    }

    private boolean isValidUser(User user) {
        Member member = user.getMember();
        return user != null && member.getType() == MemberType.USER;
    }

    private boolean isValidPartner(Partner partner) {
        return partner != null && partner.getMember().getType() == MemberType.PARTNER;
    }
}
