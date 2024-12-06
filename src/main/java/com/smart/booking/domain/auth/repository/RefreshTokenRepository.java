package com.smart.booking.domain.auth.repository;

import com.smart.booking.domain.auth.entity.RefreshToken;
import com.smart.booking.domain.member.entity.Member;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    RefreshToken findByMember(Member member);

    RefreshToken findByToken(String token);

    void deleteByMember(@NonNull Member member);
}
