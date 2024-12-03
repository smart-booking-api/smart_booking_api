package com.smart.booking.data.user.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.user.entity.QThirdPartyAccount;
import com.smart.booking.domain.user.entity.QUser;
import com.smart.booking.domain.user.entity.QUserProfile;
import com.smart.booking.domain.user.entity.User;
import com.smart.booking.domain.user.enums.ThirdPartyAccountProvider;
import com.smart.booking.domain.user.enums.UserStatus;
import com.smart.booking.domain.user.repository.UserRepositoryCustom;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QUser qUser = QUser.user;
    private final QUserProfile qUserProfile = QUserProfile.userProfile;
    private final QThirdPartyAccount qThirdPartyAccount = QThirdPartyAccount.thirdPartyAccount;

    @Override
    public @NonNull CursorResult<User> findByNicknameAndStatusWithCursor(
        String nickname,
        UserStatus status,
        String cursor,
        int pageSize
    ) {

        final List<User> users = queryFactory.select(qUser)
            .from(qUserProfile)
            .rightJoin(qUserProfile.user, qUser)
            .where(idLessThan(cursor), nicknameContains(nickname))
            .orderBy(qUser.id.desc())
            .limit(pageSize + 1)
            .fetch();

        final int totalCount = queryFactory.selectFrom(qUser)
            .where(statusEq(status), nicknameContains(nickname))
            .fetch()
            .size();

        final boolean hasNext = users.size() > pageSize;

        return new CursorResult<>(
            hasNext ? users.subList(0, pageSize) : users,
            hasNext,
            totalCount
        );
    }

    @Override
    public Optional<User> findByProviderUserIdAndProvider(String providerUserId, ThirdPartyAccountProvider provider) {
        return Optional.ofNullable(queryFactory.select(qUser)
            .from(qUser)
            .innerJoin(qUser.thirdPartyAccount, qThirdPartyAccount)
            .where(qThirdPartyAccount.providerUserId.eq(providerUserId).and(qThirdPartyAccount.provider.eq(provider)))
            .fetchFirst());
    }

    BooleanExpression idLessThan(String cursor) {
        return cursor == null ? null : qUser.id.lt(cursor);
    }

    BooleanExpression statusEq(UserStatus status) {
        return status == null ? null : qUser.status.eq(status);
    }

    BooleanExpression nicknameContains(String nickname) {
        return nickname == null ? null : qUserProfile.nickname.contains(nickname);
    }
}
