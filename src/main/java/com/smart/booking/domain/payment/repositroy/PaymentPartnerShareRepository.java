package com.smart.booking.domain.payment.repositroy;

import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.entity.PaymentPartnerShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentPartnerShareRepository extends JpaRepository<PaymentPartnerShare, String> {

}
