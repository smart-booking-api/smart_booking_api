package com.smart.booking.facade.mapper;

import com.smart.booking.domain.reservation.entity.Reservation;
import com.smart.booking.facade.dto.reservation.ReservationSimpleResponse;
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
    @Mapping(target = "teeBoxNumber", source = "box.number")
    ReservationSimpleResponse reservationToDto(Reservation reservation);

    @IterableMapping(qualifiedByName = "E2R")
    List<ReservationSimpleResponse> reservationsToDtoList(List<Reservation> lecture);
}
