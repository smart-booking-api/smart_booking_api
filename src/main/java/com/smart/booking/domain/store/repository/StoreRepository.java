package com.smart.booking.domain.store.repository;

import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.store.entity.Store;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String>, StoreRepositoryCustom {

    @NonNull List<Store> findByRegion(Region region);


}
