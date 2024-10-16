package com.smart.booking.domain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    protected OffsetDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    protected String createdBy;
    @LastModifiedDate
    protected OffsetDateTime updatedAt;
    @LastModifiedBy
    protected String updatedBy;
    @Builder.Default
    @Version
    protected Integer version = 0;


}
