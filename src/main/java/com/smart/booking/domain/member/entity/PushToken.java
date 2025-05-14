package com.smart.booking.domain.member.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "push_token")
public class PushToken extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(unique = true)
    private String token;

    void linkToMember(Member member) {
        this.member = member;
    }

    void unlinkFromMember() {
        this.member = null;
    }
}
