package com.smart.booking.domain.payment.service;

import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.payment.dto.PartnerSettlementDto;
import com.smart.booking.domain.payment.dto.PartnerTeeBoxSettlementDto;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public interface PaymentSettlementService {

    /**
     * 특정 기간 동안 Partner별 정산 조회
     *
     * @param startDate 정산 시작 날짜
     * @param endDate   정산 종료 날짜
     *
     * @return Partner별 정산 금액 합계
     */
    PartnerSettlementDto getPartnerSettlement(OffsetDateTime startDate, OffsetDateTime endDate);

    /**
     * 특정 기간 동안 Partner 및 TeeBox별 정산 조회
     *
     * @param partner   파트너
     * @param teeBox    teeBox
     * @param startDate 정산 시작 날짜
     * @param endDate   정산 종료 날짜
     *
     * @return Partner 및 TeeBox별 정산 금액 합계
     */
    PartnerTeeBoxSettlementDto getPartnerTeeBoxSettlement(Partner partner, TeeBox teeBox, OffsetDateTime startDate, OffsetDateTime endDate);

    /**
     * 특정 Partner의 정산 금액 조회
     *
     * @param partner   Partner 객체
     * @param startDate 정산 시작 날짜
     * @param endDate   정산 종료 날짜
     *
     * @return Partner의 정산 금액 합계
     */
    PartnerSettlementDto getSettlementByPartner(Partner partner, OffsetDateTime startDate, OffsetDateTime endDate);


}
