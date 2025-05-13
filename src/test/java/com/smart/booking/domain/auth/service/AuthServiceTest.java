package com.smart.booking.domain.auth.service;

import com.smart.booking.common.cipher.SecureString;
import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.member.enums.MemberType;
import com.smart.booking.domain.member.repository.MemberRepository;
import com.smart.booking.domain.user.entity.ThirdPartyAccount;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.enums.UserStatus;
import com.smart.booking.domain.user.repository.ThirdPartyAccountRepository;
import com.smart.booking.domain.user.repository.UserRepository;
import com.smart.booking.domain.user.service.UserService;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ThirdPartyAccountRepository thirdPartyAccountRepository;

    @BeforeEach
    void setUp() {
        Member member = new Member(null, MemberType.USER);
        memberRepository.save(member);

        User user = new User(
            null,
            null,
            null,
            "kkk@naver.com",
            SecureString.of("01033333333"),
            null, null,
            member,
            UserStatus.ACTIVE,
            OffsetDateTime.now(),
            OffsetDateTime.now()
        );

        userRepository.save(user);

        ThirdPartyAccount thirdPartyAccount = new ThirdPartyAccount(null, user, ThirdPartyAccountProvider.KAKAO, "aaa777");
        thirdPartyAccountRepository.save(thirdPartyAccount);
    }

}