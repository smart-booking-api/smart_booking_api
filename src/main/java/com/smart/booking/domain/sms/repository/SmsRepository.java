package com.smart.booking.domain.sms.repository;

import com.smart.booking.domain.sms.entity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository extends JpaRepository<Sms, String> {

}
