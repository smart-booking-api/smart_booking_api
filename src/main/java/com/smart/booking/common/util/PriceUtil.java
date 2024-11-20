package com.smart.booking.common.util;

public class PriceUtil {

    private static final int VAT_RATE = 10;

    /**
     * 공급가액과 부가세를 계산하는 유틸리티 메서드
     *
     * @param totalAmount 총 금액 (Integer)
     *
     * @return 계산된 공급가액과 부가세를 포함한 AmountResult 객체
     */
    public static AmountResult calculateAmounts(int totalAmount) {
        int supplyAmount = (totalAmount * 100) / (100 + VAT_RATE);
        int vatAmount = totalAmount - supplyAmount;

        return new AmountResult(supplyAmount, vatAmount);
    }

    /**
     * 내부 클래스를 통해 결과를 반환 (DTO 패턴)
     */
    public static class AmountResult {

        private final int supplyAmount;
        private final int vatAmount;

        public AmountResult(int supplyAmount, int vatAmount) {
            this.supplyAmount = supplyAmount;
            this.vatAmount = vatAmount;
        }

        public int getSupplyAmount() {
            return supplyAmount;
        }

        public int getVatAmount() {
            return vatAmount;
        }
    }
}
