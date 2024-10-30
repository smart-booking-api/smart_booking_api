package com.smart.booking.data.store.repository;

import static com.smart.booking.domain.store.entity.QStore.store;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.repository.StoreRepositoryCustom;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public @NonNull CursorResult<Store> findByNameAndRegionWithCursor(
        String name,
        Region region,
        int pageSize,
        String cursor
    ) {

        final BooleanBuilder whereClause = new BooleanBuilder();

        if (cursor != null) {
            whereClause.and(store.id.lt(cursor));
        }

        if (name != null) {
            whereClause.and(store.name.containsIgnoreCase(name));
        }

        if (region != null) {
            whereClause.and(store.region.eq(region));
        }

        final List<Store> stores = jpaQueryFactory.selectFrom(store)
            .where(whereClause)
            .orderBy(store.id.desc())
            .limit(pageSize + 1)
            .fetch();

        final int totalCount = jpaQueryFactory.selectFrom(store)
            .where(whereClause)
            .fetch()
            .size();

        final Pageable pageable = PageRequest.of(
            0,
            pageSize,
            Sort.by(Sort.Order.desc("id"))
        );

        return new CursorResult<>(
            stores.subList(0, pageSize),
            stores.size() > pageSize,
            totalCount
        );

    }


}
