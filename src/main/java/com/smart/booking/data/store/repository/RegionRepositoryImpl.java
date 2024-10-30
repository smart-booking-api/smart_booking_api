package com.smart.booking.data.store.repository;

import com.smart.booking.data.store.data_source.RegionDataSource;
import com.smart.booking.data.store.model.ParseRegionResultDto;
import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.store.repository.RegionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RegionRepositoryImpl implements RegionRepository {

    private final RegionDataSource regionDataSource;

    public Region parseRegion(@NonNull String address) {
        final ParseRegionResultDto result = regionDataSource.parseRegion(address);
        
        if (result.documents().isEmpty()) {
            return null;
        }

        return Region.fromFullValue(result.documents().getFirst().address().region_1depth_name());
    }
}
