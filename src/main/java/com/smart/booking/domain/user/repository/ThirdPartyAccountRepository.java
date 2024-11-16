package com.smart.booking.domain.user.repository;

import com.smart.booking.domain.user.entity.ThirdPartyAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ThirdPartyAccountRepository extends JpaRepository<ThirdPartyAccount, String> {

}
