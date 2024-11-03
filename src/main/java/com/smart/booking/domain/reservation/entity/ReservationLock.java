package com.smart.booking.domain.reservation.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@RedisHash(value = "reservation", timeToLive=180)
public class ReservationLock {
    @Id
    private String key;
    private String memberId;
}
