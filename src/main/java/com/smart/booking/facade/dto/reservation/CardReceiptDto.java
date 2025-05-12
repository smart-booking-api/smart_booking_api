package com.smart.booking.facade.dto.reservation;

import com.smart.booking.domain.payment.dto.PaymentResponseDto;
import com.smart.booking.domain.reservation.entity.Reservation;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CardReceiptDto {
    @Schema(description = "예약정보")
    private ReservationInfo reservationInfo;
    @Schema(description = "결제정보")
    private PaymentInfo paymentInfo;
    @Schema(description = "판매자정보")
    private SellerInfo sellerInfo;
    @Schema(description = "가맹점정보")
    private StoreInfo storeInfo;
    @Schema(description = "금액정보")
    private AmountInfo amountInfo;

    @Builder
    public CardReceiptDto(ReservationInfo reservationInfo, PaymentInfo paymentInfo, SellerInfo sellerInfo, StoreInfo storeInfo,
        AmountInfo amountInfo) {
        this.reservationInfo = reservationInfo;
        this.paymentInfo = paymentInfo;
        this.sellerInfo = sellerInfo;
        this.storeInfo = storeInfo;
        this.amountInfo = amountInfo;
    }

    @Getter
    public static class ReservationInfo {
        @Schema(description = "예약번호")
        private int reservationNo;
        @Schema(description = "예약자")
        private String reservationUserName;
        @Schema(description = "예약상품")
        private String reservationProductName;

        public ReservationInfo(Reservation reservation, String startTime, String endTime) {
            this.reservationNo = reservation.getReservationNo();
            this.reservationUserName = reservation.getReservationMember().getReservationUserName();
            this.reservationProductName = getReservationProductName(reservation, startTime, endTime);
        }

        private static String getReservationProductName(Reservation reservation, String startTime, String endTime) {
            StringBuilder sb = new StringBuilder();
            sb.append(reservation.getStore().getName());
            sb.append(" ");
            sb.append(reservation.getTeeBox().getName());
            sb.append(" ");
            sb.append(startTime);
            sb.append("-");
            sb.append(endTime);
            return sb.toString();
        }
    }

    @Getter
    public static class PaymentInfo {
        @Schema(description = "카드사")
        private String cardCompany;
        @Schema(description = "카드번호")
        private String cardNo;
        @Schema(description = "거래타입")
        private String payType;
        @Schema(description = "결제상태")
        private String payStatus;
        @Schema(description = "승인번호")
        private String approvalNo;
        @Schema(description = "결제일시")
        private int payDateTime;

        public PaymentInfo(PaymentResponseDto paymentResponseDto) {
            this.cardCompany = paymentResponseDto.cardName();
            this.cardNo = paymentResponseDto.cardNumber();
            this.payType = paymentResponseDto.payMethod();
            this.payStatus = paymentResponseDto.status();
            this.approvalNo = paymentResponseDto.applyNum();
            this.payDateTime = paymentResponseDto.paidAt();
        }
    }

    public static class SellerInfo {
        @Schema(description = "판매자명")
        private String sellerName;
        @Schema(description = "대표자명")
        private String representativeName;
        @Schema(description = "사업자등록번호")
        private String businessNo;
        @Schema(description = "전화번호")
        private String telNo;
        @Schema(description = "사업장주소")
        private String businessAddress;

        public SellerInfo(PaymentResponseDto paymentResponseDto) {
            this.sellerName = "";
            this.representativeName = "";
            this.businessNo = "";
            this.telNo = "";
            this.businessAddress = "";
        }
    }

    public static class StoreInfo {
        @Schema(description = "가맹점명")
        private String storeName;
        @Schema(description = "대표자명")
        private String representativeName;
        @Schema(description = "사업자등록번호")
        private String businessNo;
        @Schema(description = "전화번호")
        private String telNo;
        @Schema(description = "사업장주소")
        private String businessAddress;

        public StoreInfo(PaymentResponseDto paymentResponseDto) {
            this.storeName = paymentResponseDto.userAgent();
            this.representativeName = "";
            this.businessNo = "";
            this.telNo = "";
            this.businessAddress = "";
        }
    }

    @NoArgsConstructor
    public static class AmountInfo {
        @Schema(description = "공급가액")
        private BigDecimal supplyAmount;
        @Schema(description = "면세가액")
        private BigDecimal dutyFreeAmount;
        @Schema(description = "부가세액")
        private BigDecimal vatAmount;
        @Schema(description = "과세제외액")
        private BigDecimal taxExceptionAmount;

        public AmountInfo(PaymentResponseDto paymentResponseDto) {
            this.supplyAmount = paymentResponseDto.amount();
            this.dutyFreeAmount = BigDecimal.valueOf(0);
            this.vatAmount = BigDecimal.valueOf(0);
            this.taxExceptionAmount = BigDecimal.valueOf(0);
        }
    }
}
