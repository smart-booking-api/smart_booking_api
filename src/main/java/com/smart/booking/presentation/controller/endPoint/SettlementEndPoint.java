package com.smart.booking.presentation.controller.endPoint;

public class SettlementEndPoint {

    public static final String SETTLEMENT_ROOT = "/api/settlement/v1";
    public static final String MASTER_SETTLEMENT_BY_PARTNER_URL = SETTLEMENT_ROOT + "/partners/{memberId}/master";
    public static final String SETTLEMENT_BY_PARTNER_URL = SETTLEMENT_ROOT + "/partners/{memberId}";
    public static final String SETTLEMENT_BY_TEE_BOX_URL = SETTLEMENT_ROOT + "/partners/{memberId}/tee-box/{teeBoxId}";


}
