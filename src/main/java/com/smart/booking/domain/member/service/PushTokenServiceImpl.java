package com.smart.booking.domain.member.service;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.entity.PushToken;
import com.smart.booking.domain.member.repository.MemberRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PushTokenServiceImpl implements PushTokenService {

    private final MemberRepository memberRepository;

    @Override
    public @NonNull PushToken addPushToken(@NonNull Member member, @NonNull String token) {
        final var pushToken = PushToken.builder().token(token).build();

        member.addPushToken(pushToken);

        memberRepository.save(member);

        return pushToken;
    }

    @Override
    public @NonNull List<PushToken> getPushTokensByMember(@NonNull Member member) {
        return member.getPushTokens();
    }

    @Override
    public void removePushTokenByToken(@NonNull Member member, @NonNull String token) {
        member.removePushToken(token);

        memberRepository.save(member);
    }

    @Override
    public void removeAllPushTokensByMember(@NonNull Member member) {
        member.removeAllPushTokens();

        memberRepository.save(member);
    }
}
