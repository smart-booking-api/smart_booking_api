package com.smart.booking.data.partner.controller;

import static com.smart.booking.controller.endPoint.PaymentEndPoint.PAYMENT_COMPLETE_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.booking.facade.dto.payment.CompletePaymentRequestDto;
import com.smart.booking.facade.pg.CompletePaymentFacade;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PaymentControllerTests {

    @Autowired
    private CompletePaymentFacade completePaymentFacade;

    @Test
    @Transactional
    @DisplayName("결제완료 callback test")
    @Disabled
    public void paymentCompleteTest() {

        String testMemberId = "0acd1e64-5fe3-47a1-8960-f1d48e2c790f";

        var request = CompletePaymentRequestDto.builder()
            .memberId(testMemberId)
            .merchantUid("384f0559-928f-4008-a73f-c71664517f79")
            .trackingId("test")
            .teeBoxId("test")
            .startTimeTableId("test")
            .amount(1000)
            .reservationUserName("test")
            .reservationUserPhoneNum("test")
            .build();

        completePaymentFacade.exceuete(request);
    }
}
