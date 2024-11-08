package com.smart.booking.data.payment.respository;

import static org.assertj.core.api.Assertions.assertThat;

import com.smart.booking.domain.external.dto.SearchPaymentInfoRequestDto;
import com.smart.booking.domain.payment.repositroy.ExternalPaymentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ExternalPaymentRepositoryImplTests {

    @Autowired
    private ExternalPaymentRepository externalPaymentRepository;

    @Test
    @DisplayName("access_token 발급")
    void getTokenTest() throws Exception {
        externalPaymentRepository.getToken();
    }

    @Test
    @DisplayName("결제 내역 단건 조회")
    void getPaymentInfoTest() throws Exception {
        externalPaymentRepository.searchPaymentInfo(new SearchPaymentInfoRequestDto("384f0559-928f-4008-a73f-c71664517f79", "-started"));
    }


}
