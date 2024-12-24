package com.smart.booking.presentation.controller.endPoint;

public abstract class PartnerStoreEndPoint {

    public static final String BASE = "/api/partner";
    public static final String V1 = BASE + "/v1/stores";

    public static final String GET_MY_STORE = V1 + "/me";

    public static final String GET_STORES = V1;
    public static final String GET_STORE = V1 + "/{storeId}";
    public static final String CREATE_STORE = V1;
    public static final String UPDATE_STORE = V1 + "/{storeId}";
    public static final String DELETE_STORE = V1 + "/{storeId}";

    public static final String GET_TEE_BOXES = V1 + "/{storeId}/tee-boxes";
    public static final String GET_TEE_BOX = V1 + "/{storeId}/tee-boxes/{teeBoxId}";
    public static final String CREATE_TEE_BOX = V1 + "/{storeId}/tee-boxes";
    public static final String UPDATE_TEE_BOX = V1 + "/{storeId}/tee-boxes/{teeBoxId}";
    public static final String DELETE_TEE_BOX = V1 + "/{storeId}/tee-boxes/{teeBoxId}";

    public static final String GET_UNLINKED_DEVICES = V1 + "/{storeId}/devices";
}
