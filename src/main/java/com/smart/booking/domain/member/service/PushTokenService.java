package com.smart.booking.domain.member.service;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.entity.PushToken;
import java.util.List;
import lombok.NonNull;

public interface PushTokenService {

    @NonNull
    PushToken addPushToken(@NonNull Member member, @NonNull String token);

    @NonNull
    List<PushToken> getPushTokensByMember(@NonNull Member member);

    void removePushTokenByToken(@NonNull Member member, @NonNull String token);

    void removeAllPushTokensByMember(@NonNull Member member);


}
