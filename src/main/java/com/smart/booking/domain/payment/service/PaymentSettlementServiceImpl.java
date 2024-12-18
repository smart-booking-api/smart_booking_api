package com.smart.booking.domain.payment.service;

import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.payment.dto.PartnerSettlementDto;
import com.smart.booking.domain.payment.dto.PartnerTeeBoxSettlementDto;
import com.smart.booking.domain.payment.entity.PaymentPartnerShare;
import com.smart.booking.domain.payment.entity.PaymentStatus;
import com.smart.booking.domain.payment.repositroy.PaymentPartnerShareRepository;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentSettlementServiceImpl implements PaymentSettlementService {

    private final PaymentPartnerShareRepository paymentPartnerShareRepository;


    @Override
    public PartnerSettlementDto getPartnerSettlement(OffsetDateTime startDate, OffsetDateTime endDate) {
        var shares = paymentPartnerShareRepository.findByCreatedAtBetween(startDate, endDate);

        // 유효한 상태로 필터링하고 각 금액 합계 계산
        int totalAmount = shares.stream()
            .filter(share -> share.getPayment().isValidStatusForSettlement()) // 유효한 상태 필터링
            .mapToInt(share -> applyRefundAdjustment(share.getPayment().getPaymentStatus(), share.getTotalAmount()))
            .sum();

        int supplyAmount = shares.stream()
            .filter(share -> share.getPayment().isValidStatusForSettlement())
            .mapToInt(share -> applyRefundAdjustment(share.getPayment().getPaymentStatus(), share.getSupplyAmount()))
            .sum();

        int vatAmount = shares.stream()
            .filter(share -> share.getPayment().isValidStatusForSettlement())
            .mapToInt(share -> applyRefundAdjustment(share.getPayment().getPaymentStatus(), share.getVatAmount()))
            .sum();

        // payment 건수 계산
        var paymentCount = shares.stream()
            .filter(share -> share.getPayment().isValidStatusForSettlement())
            .count();

        // 단일 DTO로 반환
        return new PartnerSettlementDto(
            shares.isEmpty() ? null : shares.get(0).getPartner(),
            totalAmount,
            supplyAmount,
            vatAmount,
            (int) paymentCount
        );
    }

    public PartnerTeeBoxSettlementDto getPartnerTeeBoxSettlement(
        @NonNull Partner partner,
        @NonNull TeeBox teeBox,
        @NonNull OffsetDateTime startDate,
        @NonNull OffsetDateTime endDate
    ) {
        List<PaymentPartnerShare> shares = paymentPartnerShareRepository.findByPartnerAndCreatedAtBetween(partner, startDate, endDate);

        // 유효한 상태 필터링 후 teeBoxId로 필터링
        List<PaymentPartnerShare> filteredShares = shares.stream()
            .filter(share -> share.getPayment().isValidStatusForSettlement()) // 상태 필터링
            .filter(share -> teeBox.equals(share.getPayment().getTeeBox()))
            .toList();

        // 금액 합계 계산
        var totalAmount = filteredShares.stream()
            .mapToInt(share -> applyRefundAdjustment(share.getPayment().getPaymentStatus(), share.getTotalAmount()))
            .sum();
        var supplyAmount = filteredShares.stream()
            .mapToInt(share -> applyRefundAdjustment(share.getPayment().getPaymentStatus(), share.getSupplyAmount()))
            .sum();
        var vatAmount = filteredShares.stream()
            .mapToInt(share -> applyRefundAdjustment(share.getPayment().getPaymentStatus(), share.getVatAmount()))
            .sum();

        // payment 건수 계산
        var paymentCount = filteredShares.size();

        // 단일 DTO로 반환
        return new PartnerTeeBoxSettlementDto(
            partner,
            teeBox,
            totalAmount,
            supplyAmount,
            vatAmount,
            paymentCount
        );
    }

    @Override
    public PartnerSettlementDto getSettlementByPartner(Partner partner, OffsetDateTime startDate, OffsetDateTime endDate) {
        var shares = paymentPartnerShareRepository.findByPartnerAndCreatedAtBetween(partner, startDate, endDate);

        // 각 금액 합계 계산
        var totalAmount = shares.stream()
            .filter(share -> share.getPayment().isValidStatusForSettlement()) // 유효한 상태 필터링
            .mapToInt(share -> applyRefundAdjustment(share.getPayment().getPaymentStatus(), share.getTotalAmount()))
            .sum();

        var supplyAmount = shares.stream()
            .filter(share -> share.getPayment().isValidStatusForSettlement())
            .mapToInt(share -> applyRefundAdjustment(share.getPayment().getPaymentStatus(), share.getSupplyAmount()))
            .sum();

        var vatAmount = shares.stream()
            .filter(share -> share.getPayment().isValidStatusForSettlement())
            .mapToInt(share -> applyRefundAdjustment(share.getPayment().getPaymentStatus(), share.getVatAmount()))
            .sum();

        // payment 건수 계산
        var paymentCount = shares.stream()
            .filter(share -> share.getPayment().isValidStatusForSettlement())
            .count();

        // 단일 DTO로 반환
        return new PartnerSettlementDto(partner, totalAmount, supplyAmount, vatAmount, (int) paymentCount);

    }

    /**
     * REFUND 상태인 경우 음수 처리
     */
    private int applyRefundAdjustment(PaymentStatus status, int amount) {
        if (PaymentStatus.REFUND.equals(status)) {
            return -amount; // REFUND 상태는 음수로 처리
        }
        return amount;
    }

}
