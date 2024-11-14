package com.smart.booking.domain.tee_box.service;

import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.repositroy.TeeBoxRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class TeeBoxCommonServiceImpl implements TeeBoxCommonService {

    private final TeeBoxRepository teeBoxRepository;

    @Override
    public @NonNull TeeBox getTeeBoxById(@NonNull String id) {
        return null;
    }

    @Override
    public @NonNull List<TeeBox> getTeeBoxesByStoreId(@NonNull String storeId) {
        return null;
    }
}
