package com.smart.booking.facade.mapper;

import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponseDto;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Named("E2R")
    @Mapping(target = "storeName", source = "store.name")
    @Mapping(target = "teeBoxNumber", source = "teeBox.number")
    @Mapping(target = "startTime", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    ReservationSimpleResponseDto reservationToDto(Reservation reservation);

    @IterableMapping(qualifiedByName = "E2R")
    List<ReservationSimpleResponseDto> reservationsToDtoList(List<Reservation> lecture);
}
