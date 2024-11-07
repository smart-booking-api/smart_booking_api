package com.smart.booking.data.store.repository;


import com.smart.booking.domain.common.enums.Region;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.store.repository.StoreRepository;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class StoreRepositoryImplTests {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    void getStores() {

        final var stores = IntStream.range(0, 40).boxed().map(
            i -> storeRepository.save(Store.builder()
                .region(Region.CHUNGBUK)
                .name("남청주IC직영샵" + i)
                .build())
        ).toList();

        final var cursorResult = storeRepository.findByNameAndRegionWithCursor(null, null, 20, null);

        System.out.println(cursorResult.content());
        System.out.println(cursorResult.hasNext());
        System.out.println(cursorResult.totalCount());


    }

}
