package com.smart.booking.domain.holiday.repository;

import com.smart.booking.domain.holiday.entity.Holiday;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, String> {

    @NonNull List<Holiday> findAllByDateBetween(@NonNull OffsetDateTime start, @NonNull OffsetDateTime end);
}
