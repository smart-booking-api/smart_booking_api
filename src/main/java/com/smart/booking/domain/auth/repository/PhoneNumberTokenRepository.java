package com.smart.booking.domain.auth.repository;

import com.smart.booking.domain.auth.entity.PhoneNumberToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberTokenRepository extends JpaRepository<PhoneNumberToken, String> {

}
