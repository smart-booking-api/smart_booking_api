package com.smart.booking.domain.store.repository;

import com.smart.booking.domain.common.entity.BusinessRegistration;
import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.store.entity.Store;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, String>, StoreRepositoryCustom {

    @NonNull
    List<Store> findByRegion(Region region);


    boolean existsByBusinessRegistration(@NonNull BusinessRegistration businessRegistration);


    Optional<Store> findByBusinessRegistration(@NonNull BusinessRegistration businessRegistration);
}
