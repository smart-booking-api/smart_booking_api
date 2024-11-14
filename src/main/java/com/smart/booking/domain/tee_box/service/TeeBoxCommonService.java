package com.smart.booking.domain.tee_box.service;

import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.util.List;
import lombok.NonNull;

public interface TeeBoxCommonService {

    @NonNull TeeBox getTeeBoxById(@NonNull String id);

    @NonNull List<TeeBox> getTeeBoxesByStoreId(@NonNull String storeId);


}
