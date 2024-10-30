package com.smart.booking.domain.payment.service;

public interface MerchantInfoService {
    /**
     * 가맹점 정보 조회
     * request : paymentId
     * response : 가맹점명
     * 대표자명
     * 사업자등록번호
     * 전화번호
     * 사업장 주소
     * */
    Object getMerchantInfo(Long merchantId);
}
