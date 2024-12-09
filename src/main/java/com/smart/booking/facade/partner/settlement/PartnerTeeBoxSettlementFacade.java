package com.smart.booking.facade.partner.settlement;

import com.smart.booking.domain.partner.service.PartnerService;
import com.smart.booking.domain.payment.service.PaymentSettlementService;
import com.smart.booking.domain.tee_box.service.TeeBoxCommonService;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PartnerTeeBoxSettlementFacade {


    private final PaymentSettlementService paymentSettlementService;
    private final PartnerService partnerService;
    private final TeeBoxCommonService teeBoxCommonService;

    /**
     * 파트너와 TeeBox별 정산 조회
     *
     * @param PartnerTeeBoxSettlementFacadeRequest request
     *
     * @return 파트너와 TeeBox별 정산 DTO
     */
    public PartnerTeeBoxSettlementFacadeResponse getPartnerTeeBoxSettlement(PartnerTeeBoxSettlementFacadeRequest request) {
        var partner = partnerService.getPartner(request.getPartnerId());
        var teeBox = teeBoxCommonService.getTeeBoxById(request.getTeeBoxId());

        // 정산 정보 조회
        var response = paymentSettlementService.getPartnerTeeBoxSettlement(partner, teeBox, request.getStartDate(), request.getEndDate());

        // DTO 매핑
        return new PartnerTeeBoxSettlementFacadeResponse(
            response.getPartner().getCompany().getName(),
            teeBox.getName(),  // TeeBox 이름
            response.getTotalAmount(),
            response.getSupplyAmount(),
            response.getVatAmount(),
            response.getPaymentCount()
        );
    }

    @Getter
    @AllArgsConstructor
    public static class PartnerTeeBoxSettlementFacadeRequest {

        private String partnerId;
        private String teeBoxId;
        private OffsetDateTime startDate;
        private OffsetDateTime endDate;

    }

    @Getter
    @AllArgsConstructor
    public static class PartnerTeeBoxSettlementFacadeResponse {

        private String partnerName;
        private String teeBoxName;
        private Integer totalAmount;
        private Integer supplyAmount;
        private Integer vatAmount;
        private int paymentCount;

    }
}
