package com.smart.booking.domain.store.repository;

import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.entity.StoreClosedDay;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreClosedDayRepository extends JpaRepository<StoreClosedDay, String> {

    boolean existsByStoreAndClosedAt(@NonNull Store store, @NonNull OffsetDateTime offsetDateTime);

    List<StoreClosedDay> findAllByStoreAndClosedAtAfter(@NonNull Store store, @NonNull OffsetDateTime offsetDateTime);

    List<StoreClosedDay> findAllByStore(@NonNull Store store);


}
