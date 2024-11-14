package com.smart.booking.domain.tee_box.service;

import com.smart.booking.common.enums.ResponseCode;
import com.smart.booking.common.exception.CommonException;
import com.smart.booking.domain.partner.enums.PartnerType;
import com.smart.booking.domain.tee_box.dto.CreateTeeBoxDto;
import com.smart.booking.domain.tee_box.dto.UpdateTeeBoxDto;
import com.smart.booking.domain.tee_box.dto.UpsertTeeBoxShareDto;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.mapper.TeeBoxMapper;
import com.smart.booking.domain.tee_box.repositroy.TeeBoxRepository;
import java.util.HashMap;
import java.util.List;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class TeeBoxPartnerServiceImpl extends TeeBoxCommonServiceImpl implements TeeBoxPartnerService {

    private final TeeBoxRepository teeBoxRepository;

    public TeeBoxPartnerServiceImpl(TeeBoxRepository teeBoxRepository) {
        super(teeBoxRepository);
        this.teeBoxRepository = teeBoxRepository;
    }

    @Override
    public @NonNull TeeBox createTeeBox(@NonNull CreateTeeBoxDto createTeeBoxDto) {
        checkDuplicatedPartnerTypeThrow(createTeeBoxDto.shares());
        checkShareSumThrow(createTeeBoxDto.shares());

        final TeeBox teeBox = TeeBoxMapper.toTeeBox(createTeeBoxDto);
        teeBox.updateShares(
            createTeeBoxDto.shares().stream()
                .map(TeeBoxMapper::toTeeBoxShare)
                .toList()
        );

        return teeBoxRepository.save(teeBox);
    }

    @Override
    public @NonNull TeeBox updateTeeBox(@NonNull UpdateTeeBoxDto updateTeeBoxDto) {
        checkDuplicatedPartnerTypeThrow(updateTeeBoxDto.shares());
        checkShareSumThrow(updateTeeBoxDto.shares());

        final TeeBox teeBox = teeBoxRepository.findById(updateTeeBoxDto.id())
            .orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_TEE_BOX));

        teeBox.edit(updateTeeBoxDto);
        teeBox.updateShares(
            updateTeeBoxDto.shares().stream()
                .map(TeeBoxMapper::toTeeBoxShare)
                .toList()
        );

        return teeBoxRepository.save(teeBox);
    }

    @Override
    public void deleteTeeBox(@NonNull String id) {
        teeBoxRepository.delete(
            teeBoxRepository.findById(id)
                .orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_TEE_BOX))
        );
    }


    private void checkDuplicatedPartnerTypeThrow(@NonNull List<UpsertTeeBoxShareDto> upsertTeeBoxShareDtos) {

        final var map = new HashMap<PartnerType, Integer>();

        for (UpsertTeeBoxShareDto upsertTeeBoxShareDto : upsertTeeBoxShareDtos) {

            final PartnerType key = upsertTeeBoxShareDto.partner().getType();
            final int value = map.getOrDefault(key, 0) + 1;

            if (value > 1) {
                throw new CommonException(ResponseCode.DUPLICATE_TEE_BOX_SHARE_PARTNER_TYPE);
            }

            map.put(key, value);
        }

    }

    private void checkShareSumThrow(@NonNull List<UpsertTeeBoxShareDto> upsertTeeBoxShareDtos) {
        final int sharesSum = upsertTeeBoxShareDtos.stream()
            .map(UpsertTeeBoxShareDto::share)
            .reduce(0, Integer::sum);

        if (sharesSum > 100) {
            throw new CommonException(ResponseCode.EXCEED_TEE_BOX_SHARE_SUM);
        }

    }
}
