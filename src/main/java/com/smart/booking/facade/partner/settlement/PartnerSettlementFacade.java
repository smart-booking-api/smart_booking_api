package com.smart.booking.facade.partner.settlement;

import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.payment.service.PaymentSettlementService;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PartnerSettlementFacade {


    private final PaymentSettlementService paymentSettlementService;
    private final PartnerService partnerService;

    /**
     * 파트너별 정산 조회
     *
     * @param PartnerSettlementFacadeRequest request
     *
     * @return 파트너 정산 DTO
     */
    public PartnerSettlementFacadeResponse execute(String memberId, PartnerSettlementFacadeRequest request) {
        var partner = partnerService.getPartner(request.partnerId);
        var response = paymentSettlementService.getSettlementByPartner(partner, request.getStartDate(), request.getEndDate());
        return new PartnerSettlementFacadeResponse(
            response.getTotalAmount(),
            response.getSupplyAmount(),
            response.getVatAmount(),
            response.getPaymentCount()
        );
    }

    @Getter
    @AllArgsConstructor
    public static class PartnerSettlementFacadeRequest {

        private String partnerId;
        private OffsetDateTime startDate;
        private OffsetDateTime endDate;

    }

    @Getter
    @AllArgsConstructor
    public static class PartnerSettlementFacadeResponse {

        private Integer totalAmount;
        private Integer supplyAmount;
        private Integer vatAmount;
        private int paymentCount;

    }
}
