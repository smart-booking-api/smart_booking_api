package com.smart.booking.domain.sms.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Sms extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    private String phoneNumber;

    private String message;

    private String errorMessage;

    public boolean isSent() {
        return errorMessage == null;
    }

    public void occurredError(@NonNull String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
