package com.smart.booking.domain.device.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "device_red_golf")
public class DeviceRedGolf extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    @OneToOne
    @JoinColumn(name = "device_id")
    private Device device;

    private String macAddress;

    private String ipAddress;

    private String storeKey;

    private String hittingAreaKey;

    private String program;


}
