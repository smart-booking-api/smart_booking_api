package com.smart.booking.domain.partner.repository;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.partner.entity.Partner;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PartnerRepository extends JpaRepository<Partner, String>, PartnerRepositoryCustom {

    Optional<Partner> findByLoginId(String loginId);

    Optional<Partner> findByMember(Member member);

    Optional<Partner> findByMember_Id(String memberId);

    boolean existsByBusinessRegistration(@NonNull BusinessRegistration businessRegistration);
}
