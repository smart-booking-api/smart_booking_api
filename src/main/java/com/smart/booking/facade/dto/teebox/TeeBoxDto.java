package com.smart.booking.facade.dto.teebox;

import com.smart.booking.domain.common.enums.TeeBoxType;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.facade.dto.device.DeviceDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@RequiredArgsConstructor
public class TeeBoxDto {

    @NotNull
    private final String id;

    @NotNull
    private final TeeBoxType type;

    @NotNull
    private final int number;

    @NotNull
    private final String screenName;

    @NotNull
    private final List<TeeBoxShareDto> teeBoxes;

    private final DeviceDto device;

    private final String memo;

    public TeeBoxDto(@NonNull TeeBox teeBox) {
        this.id = teeBox.getId();
        this.type = teeBox.getType();
        this.number = teeBox.getNumber();
        this.screenName = teeBox.getScreenName();
        this.teeBoxes = teeBox.getShares().stream().map(TeeBoxShareDto::new).toList();
        this.device = new DeviceDto(teeBox.getDevice());
        this.memo = teeBox.getMemo();
    }

}
