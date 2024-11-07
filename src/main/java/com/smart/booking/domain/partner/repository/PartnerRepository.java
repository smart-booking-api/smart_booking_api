package com.smart.booking.domain.partner.repository;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.partner.entity.Partner;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PartnerRepository extends JpaRepository<Partner, String>, PartnerRepositoryCustom {

    Optional<Partner> findByLoginId(String loginId);
    Optional<Partner> findByMember(Member member);
}
