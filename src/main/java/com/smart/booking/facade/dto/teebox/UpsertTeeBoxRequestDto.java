package com.smart.booking.facade.dto.teebox;

import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.device.entity.Device;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.tee_box.dto.CreateTeeBoxDto;
import com.smart.booking.domain.tee_box.dto.UpdateTeeBoxDto;
import com.smart.booking.domain.tee_box.dto.UpsertTeeBoxShareDto;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpsertTeeBoxRequestDto {

    @NotNull
    private final int number;

    @NotNull
    private final String deviceId;

    @NotNull
    private final TeeBoxType type;

    @NotNull
    private final String screenName;

    @NotNull
    private final List<UpsertTeeBoxShareDto> shares;

    public CreateTeeBoxDto toCreateTeeBoxDto(@NonNull Store store, @NonNull Device device) {
        return CreateTeeBoxDto.builder()
            .store(store)
            .number(number)
            .device(device)
            .type(type)
            .screenName(screenName)
            .shares(shares)
            .build();
    }


    public UpdateTeeBoxDto toUpdateTeeBoxDto(@NonNull Device device) {
        return UpdateTeeBoxDto.builder()
            .number(number)
            .device(device)
            .type(type)
            .screenName(screenName)
            .shares(shares)
            .build();
    }
}
