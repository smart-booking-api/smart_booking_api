package com.smart.booking.domain.user.repository;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {

    Optional<User> findByEmail(@NonNull String email);

    Optional<User> findByMember(Member member);

    Optional<User> findByThirdPartyAccount_Id(@NonNull String thirdPartyAccountId);

    Optional<User> findByThirdPartyAccount_ProviderAndThirdPartyAccount_ProviderUserId(
        @NonNull ThirdPartyAccountProvider provider,
        @NonNull String providerUserId
    );
}
