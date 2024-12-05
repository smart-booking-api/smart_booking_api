package com.smart.booking.domain.tee_box.service;

import com.smart.booking.domain.tee_box.entity.TeeBox;
import lombok.NonNull;

import java.util.List;

public interface TeeBoxCommonService {

    @NonNull
    TeeBox getTeeBoxById(@NonNull String id);

    @NonNull
    List<TeeBox> getTeeBoxesByStoreId(@NonNull String storeId);


}
