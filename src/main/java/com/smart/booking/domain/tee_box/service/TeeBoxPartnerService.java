package com.smart.booking.domain.tee_box.service;

import com.smart.booking.domain.tee_box.dto.CreateTeeBoxDto;
import com.smart.booking.domain.tee_box.dto.UpdateTeeBoxDto;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import lombok.NonNull;

public interface TeeBoxPartnerService extends TeeBoxCommonService {

    @NonNull TeeBox createTeeBox(@NonNull CreateTeeBoxDto createTeeBoxDto);

    @NonNull TeeBox updateTeeBox(@NonNull UpdateTeeBoxDto updateTeeBoxDto);

    void deleteTeeBox(@NonNull String id);
}
