package com.smart.booking.data.store.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.store.entity.QStore;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.repository.StoreRepositoryCustom;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final QStore qStore = QStore.store;

    @Override
    public @NonNull CursorResult<Store> findByNameAndRegionWithCursor(
        String name,
        Region region,
        int pageSize,
        String cursor
    ) {

        final List<Store> stores = jpaQueryFactory.selectFrom(qStore)
            .where(idLessThan(cursor), nameContains(name), regionEq(region))
            .orderBy(qStore.id.desc())
            .limit(pageSize + 1)
            .fetch();

        final int totalCount = jpaQueryFactory.selectFrom(qStore)
            .where(nameContains(name), regionEq(region))
            .fetch()
            .size();

        final boolean hasNext = stores.size() > pageSize;

        return new CursorResult<>(
            hasNext ? stores.subList(0, pageSize) : stores,
            hasNext,
            totalCount
        );

    }

    BooleanExpression idLessThan(String cursor) {
        return cursor == null ? null : qStore.id.lt(cursor);
    }

    BooleanExpression nameContains(String name) {
        return name == null ? null : qStore.name.containsIgnoreCase(name);
    }

    BooleanExpression regionEq(Region region) {
        return region == null ? null : qStore.region.eq(region);
    }


}
