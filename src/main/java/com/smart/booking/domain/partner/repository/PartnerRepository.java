package com.smart.booking.domain.partner.repository;

import com.smart.booking.domain.partner.entity.Partner;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PartnerRepository extends JpaRepository<Partner, String> {
    Optional<Partner> findByLoginId(String loginId);
}
