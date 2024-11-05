package com.smart.booking.domain.user.repository;

import com.smart.booking.domain.member.entity.Member;
import com.smart.booking.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByMember(Member member);
}
