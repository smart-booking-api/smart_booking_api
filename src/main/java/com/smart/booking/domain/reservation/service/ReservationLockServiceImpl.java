package com.smart.booking.domain.reservation.service;

import com.smart.booking.domain.reservation.repository.ReservationLockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationLockServiceImpl implements ReservationLockService {
    private final ReservationLockRepository reservationLockRepository;

    /**
     * 선점락 생성
     * @param storeId
     * @param timeTableId
     */
    @Override
    @CachePut(cacheNames = "reservation", key = "#storeId + '-' + #timeTableId")
    public String createReservationLock(String storeId, String timeTableId) {
//        SecurityContextHolder.getContext().getAuthentication()
        return "kyj616";
    }
}
