package com.smart.booking.domain.auth.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "phone_number_token")
public class PhoneNumberToken extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    private String phoneNumber;

    private String code;

    private boolean verified;

    private OffsetDateTime expiredAt;

}
