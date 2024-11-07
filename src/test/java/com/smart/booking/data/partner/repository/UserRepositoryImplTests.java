package com.smart.booking.data.partner.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.partner.entity.PartnerCompany;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.partner.repository.PartnerRepository;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserRepositoryImplTests {

    @Autowired
    private PartnerRepository partnerRepository;


    @Nested
    @DisplayName("파트너 목록 조회 테스트")
    class GetPartnersTests {

        @BeforeEach
        void setUp() {
            for (int i : IntStream.range(0, 40).toArray()) {
                final Partner partner = partnerRepository.save(
                    Partner.builder()
                        .type(PartnerType.values()[i % PartnerType.values().length])
                        .company(PartnerCompany.builder().name("염동욱 " + i).build())
                        .build()
                );
            }
        }

        @AfterEach
        void clear() {
            partnerRepository.deleteAll();
        }

        @Test
        void getPartners() {

            final var partners = partnerRepository.findByTypeAndCompanyNameWithCursor(
                null, null, null, 20
            );

            assertThat(partners.content().size()).isEqualTo(20);
            assertThat(partners.totalCount()).isEqualTo(40);
        }


        @Test
        void getPartnersWithCursor() {

            final var partner = partnerRepository.findByTypeAndCompanyNameWithCursor(
                    null,
                    null,
                    null,
                    21
                )
                .content()
                .getLast();

            final var partners = partnerRepository.findByTypeAndCompanyNameWithCursor(
                null,
                null,
                partner.getId(),
                20
            );

            assertThat(partners.content().size()).isEqualTo(19);
            assertThat(partners.totalCount()).isEqualTo(40);
        }

    }


}
