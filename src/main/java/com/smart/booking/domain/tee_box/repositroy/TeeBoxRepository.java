package com.smart.booking.domain.tee_box.repositroy;

import com.smart.booking.domain.tee_box.entity.TeeBox;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeeBoxRepository extends JpaRepository<TeeBox, String> {

    List<TeeBox> findAllByStore_Id(String storeId);
}
