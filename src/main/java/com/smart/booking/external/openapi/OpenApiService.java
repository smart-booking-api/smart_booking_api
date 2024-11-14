package com.smart.booking.external.openapi;

import com.smart.booking.external.openapi.model.GetRestDayInfoDto;
import com.smart.booking.external.openapi.model.RestDayDto;
import java.util.List;
import lombok.NonNull;

public interface OpenApiService {

    @NonNull List<RestDayDto> getRestDayInfo(@NonNull GetRestDayInfoDto getRestDayInfoDto);
}
