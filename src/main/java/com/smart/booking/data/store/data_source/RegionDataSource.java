package com.smart.booking.data.store.data_source;

import com.smart.booking.data.store.model.ParseRegionResultDto;
import lombok.NonNull;

public interface RegionDataSource {

    @NonNull ParseRegionResultDto parseRegion(@NonNull String address);
}
