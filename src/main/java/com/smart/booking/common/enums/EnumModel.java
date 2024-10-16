package com.smart.booking.common.enums;

import lombok.NonNull;

public interface EnumModel {

    @NonNull String getKey();

    @NonNull String getValue();
}
