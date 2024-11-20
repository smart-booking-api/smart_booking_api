package com.smart.booking.domain.tee_box.service;

import com.smart.booking.domain.tee_box.repositroy.TeeBoxRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class TeeBoxUserServiceImpl extends TeeBoxCommonServiceImpl implements TeeBoxUserService {

    private final TeeBoxRepository teeBoxRepository;

    public TeeBoxUserServiceImpl(TeeBoxRepository teeBoxRepository) {
        super(teeBoxRepository);
        this.teeBoxRepository = teeBoxRepository;
    }

}
