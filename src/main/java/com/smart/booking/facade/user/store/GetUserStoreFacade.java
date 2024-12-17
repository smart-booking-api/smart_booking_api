package com.smart.booking.facade.user.store;

import com.smart.booking.common.dto.CommonResponse;
import com.smart.booking.common.dto.MemberContextDto;
import com.smart.booking.domain.member.service.MemberService;
import com.smart.booking.domain.store.entity.Store;
import com.smart.booking.domain.tee_box.entity.TeeBox;
import com.smart.booking.domain.tee_box.service.TeeBoxUserService;
import com.smart.booking.domain.user.service.UserUserService;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUserStoreFacade {

    private final MemberService memberService;
    private final UserUserService userService;
    private final TeeBoxUserService teeBoxService;

    @NonNull
    @Transactional(readOnly = true)
    public GetUserStoreResponse execute(@NonNull MemberContextDto memberContextDto) {

        final var member = memberService.getMemberByIdOrThrow(memberContextDto.getMemberId());
        final var user = userService.getUserByMember(member);
        final var store = user.getStore();

        if (store == null) {
            return new GetUserStoreResponse(null);
        }

        final var teeBoxes = teeBoxService.getTeeBoxesByStoreId(store.getId());


        return new GetUserStoreResponse(new UserStoreDto(store, teeBoxes));

    }

    public static class GetUserStoreResponse extends CommonResponse<UserStoreDto> {
        public GetUserStoreResponse(UserStoreDto userStoreDto) {
            super(userStoreDto);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class UserStoreDto {
        @NotNull
        private final String id;

        @NonNull
        private final String name;

        @NotNull
        private final String address;


        @NotNull
        private final int teeBoxCount;

        public UserStoreDto(
                @NonNull Store store,
                @NonNull List<TeeBox> teeBoxes
        ) {
            this.id = store.getId();
            this.teeBoxCount = teeBoxes.size();
            this.name = store.getName();
            this.address = store.getAddress();
        }
    }
}
