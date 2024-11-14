package com.smart.booking.domain.tee_box.mapper;

import com.smart.booking.domain.tee_box.dto.CreateTeeBoxDto;
import com.smart.booking.domain.tee_box.dto.UpsertTeeBoxShareDto;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.entity.TeeBoxShare;
import lombok.NonNull;

public interface TeeBoxMapper {

    static TeeBox toTeeBox(@NonNull CreateTeeBoxDto createTeeBoxDto) {
        return TeeBox.builder()
            .store(createTeeBoxDto.store())
            .number(createTeeBoxDto.number())
            .device(createTeeBoxDto.device())
            .type(createTeeBoxDto.type())
            .screenName(createTeeBoxDto.screenName())
            .build();
    }


    static TeeBoxShare toTeeBoxShare(@NonNull UpsertTeeBoxShareDto upsertTeeBoxShareDto) {
        return TeeBoxShare.builder()
            .partner(upsertTeeBoxShareDto.partner())
            .share(upsertTeeBoxShareDto.share())
            .build();
    }
}
