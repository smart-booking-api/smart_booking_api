package com.smart.booking.facade.dto.teebox;

import com.smart.booking.domain.partner.entity.Partner;
import com.smart.booking.domain.tee_box.entity.TeeBoxShare;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TeeBoxShareDto {

    @NotNull
    private final String id;

    @NotNull
    private final TeeBoxShareDtoPartner partner;

    private int share;

    public TeeBoxShareDto(@NonNull TeeBoxShare teeBoxShare) {
        this.id = teeBoxShare.getId();
        this.partner = new TeeBoxShareDtoPartner(teeBoxShare.getPartner());
        this.share = teeBoxShare.getShare();
    }

    @Getter
    @RequiredArgsConstructor
    public static class TeeBoxShareDtoPartner {

        @NotNull
        private final String id;

        @NotNull
        private final String name;

        public TeeBoxShareDtoPartner(@NonNull Partner partner) {
            this.id = partner.getId();
            this.name = '[' + partner.getType().getKey() + partner.getCode() + ']' + partner.getCompany().getName();
        }
    }

}
