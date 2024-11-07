package com.smart.booking.data.partner.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smart.booking.domain.common.model.CursorResult;
import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.entity.QPartner;
import com.smart.booking.domain.partner.entity.QPartnerCompany;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.repository.PartnerRepositoryCustom;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PartnerRepositoryImpl implements PartnerRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QPartner qPartner = QPartner.partner;
    private final QPartnerCompany qPartnerCompany = QPartnerCompany.partnerCompany;

    @Override
    public @NonNull CursorResult<Partner> findByTypeAndCompanyNameWithCursor(
        PartnerType type,
        String companyName,
        String cursor,
        int pageSize
    ) {

        final List<Partner> partners = queryFactory.select(qPartner)
            .from(qPartnerCompany)
            .rightJoin(qPartnerCompany.partner, qPartner)
            .where(idLessThan(cursor), typeEq(type), companyNameContains(companyName))
            .orderBy(qPartner.id.desc())
            .limit(pageSize + 1)
            .fetch();

        final int totalCount = queryFactory.selectFrom(qPartner)
            .where(typeEq(type), companyNameContains(companyName))
            .fetch()
            .size();

        final boolean hasNext = partners.size() > pageSize;

        return new CursorResult<>(
            hasNext ? partners.subList(0, pageSize) : partners,
            hasNext,
            totalCount
        );
    }

    BooleanExpression idLessThan(String cursor) {
        return cursor == null ? null : qPartner.id.lt(cursor);
    }

    BooleanExpression typeEq(PartnerType type) {
        return type == null ? null : qPartner.type.eq(type);
    }

    BooleanExpression companyNameContains(String companyName) {
        return companyName == null ? null : qPartnerCompany.name.contains(companyName);
    }
}
