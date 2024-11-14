package com.smart.booking.external.openapi.model;

import lombok.NonNull;

public record GetRestDayInfoResponseDto<T>(
    @NonNull Response response
) {

    public record Response(
        @NonNull Header header,
        @NonNull Body body
    ) {

    }

    public record Header(
        // 결과코드
        @NonNull String resultCode,
        //결과메시지
        @NonNull String resultMsg
    ) {

    }

    public record Body(

        @NonNull Items items,
        @NonNull Integer numOfRows,
        @NonNull Integer pageNo,
        @NonNull Integer totalCount

    ) {

        public record Items(
            // RestDayDto or List<RestDayDto>
            @NonNull Object item
        ) {


        }

    }


}
