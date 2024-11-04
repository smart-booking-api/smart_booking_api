package com.smart.booking.domain.reservation.dto;

import lombok.Getter;

@Getter
public class ReservationLockDto {
    @Getter
    public static class Get {
        private String storeId;
        private String timeId;
        private String key;

        public Get(String storeId, String timeId) {
            this.storeId = storeId;
            this.timeId = timeId;
            this.key = storeId + "-" + timeId;
        }
    }

    @Getter
    public static class Create {
        private String storeId;
        private String timeId;
    }

    @Getter
    public static class Delete {
        private String storeId;
        private String timeId;
    }
}
