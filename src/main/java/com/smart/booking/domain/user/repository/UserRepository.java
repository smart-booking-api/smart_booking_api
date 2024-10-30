package com.smart.booking.domain.user.repository;

import com.smart.booking.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String> {
    Boolean existsByName(String userName);

    Optional<User> findByEmail(String email);
    Optional<User> findByRefreshToken(String refreshToken);
}
