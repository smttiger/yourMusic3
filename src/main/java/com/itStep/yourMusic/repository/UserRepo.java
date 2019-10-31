package com.itStep.yourMusic.repository;

import com.itStep.yourMusic.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
    Page<User> findAll(Pageable pageable);
}
