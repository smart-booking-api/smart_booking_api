package com.smart.booking.domain.auth.repository;

import com.smart.booking.domain.auth.value_object.UserPhoneAuth;
import lombok.NonNull;

public interface UserPhoneAuthRepository {


    /**
     * 유효한 전화번호 인증 정보 조회
     */
    @NonNull
    UserPhoneAuth findByPhoneNumberOrThrow(@NonNull String phoneNumber);


    @NonNull
    UserPhoneAuth create(@NonNull String phoneNumber, @NonNull String authCode, int minutes);


    void deleteByPhoneNumber(@NonNull String phoneNumber);
}
