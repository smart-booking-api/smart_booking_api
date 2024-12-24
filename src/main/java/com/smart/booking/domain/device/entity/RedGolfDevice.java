package com.smart.booking.domain.device.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "device_red_golf",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"store_code", "client_id"})
    }
)
public class RedGolfDevice extends Device {

    @Comment("RED GOLF 매장 코드")
    private String storeCode;

    @Comment("RED GOLF 장비 (아마 소프트웨어 아이디일것 같음) ID")
    private String clientId;
}
