package com.smart.booking.data.store.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.store.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegionRepositoryImplTests {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    void parseRegion() {
        // Arrange
        final String address = "서울 강남 학동로 30길 37-14";
        // Act
        final Region region = regionRepository.parseRegion(address);

        // Assert
        assertThat(region).isEqualTo(Region.SEOUL);
    }

}
