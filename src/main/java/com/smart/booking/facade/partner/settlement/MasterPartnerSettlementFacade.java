package com.smart.booking.facade.partner.settlement;

import com.smart.booking.domain.payment.service.PaymentSettlementService;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MasterPartnerSettlementFacade {


    private final PaymentSettlementService paymentSettlementService;

    /**
     * 마스터 정산 조회
     *
     * @param MasterPartnerSettlementFacadeRequest request
     *
     * @return 파트너 정산 DTO
     */
    public MasterPartnerSettlementFacadeResponse getPartnerSettlement(MasterPartnerSettlementFacadeRequest request) {
        var response = paymentSettlementService.getPartnerSettlement(request.getStartDate(), request.getEndDate());
        return new MasterPartnerSettlementFacadeResponse(
            response.getTotalAmount(),
            response.getSupplyAmount(),
            response.getVatAmount(),
            response.getPaymentCount()
        );
    }

    @Getter
    @AllArgsConstructor
    public static class MasterPartnerSettlementFacadeRequest {

        private OffsetDateTime startDate;
        private OffsetDateTime endDate;

    }

    @Getter
    @AllArgsConstructor
    public static class MasterPartnerSettlementFacadeResponse {

        private Integer totalAmount;
        private Integer supplyAmount;
        private Integer vatAmount;
        private int paymentCount;

    }
}
