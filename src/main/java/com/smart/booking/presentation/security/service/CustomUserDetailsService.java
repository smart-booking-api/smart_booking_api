package com.smart.booking.presentation.security.service;

import com.smart.booking.presentation.security.value_object.CustomUserDetails;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.service.UserUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserUserService userUserService;
    private final PartnerService partnerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userUserService.getUserByEmailId(userId).orElse(null);

        if (isValidUser(user)) {
            return new CustomUserDetails(user.getMember().getId(), "ROLE_USER", "");
        }

        Partner partner = partnerService.getPartnerByLoginId(userId).orElse(null);
        if (isValidPartner(partner)) {
            return new CustomUserDetails(partner.getMember().getId(), "ROLE_PARTNER", partner.getPassword());
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
