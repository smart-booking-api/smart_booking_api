package com.smart.booking.domain.member.entity;

import com.smart.booking.common.annotations.TsidGenerator;
import com.smart.booking.domain.common.entity.BaseEntity;
import com.smart.booking.domain.member.enums.MemberType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @TsidGenerator
    private String id;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PushToken> pushTokens = new ArrayList<>();

    public void addPushToken(@NonNull PushToken pushToken) {
        pushToken.linkToMember(this);
        pushTokens.add(pushToken);
    }

    public void removePushToken(@NonNull String token) {
        pushTokens.removeIf(pushToken -> pushToken.getToken().equals(token));
    }

    public void removeAllPushTokens() {
        pushTokens.forEach(PushToken::unlinkFromMember);
        pushTokens.clear();
    }

}
