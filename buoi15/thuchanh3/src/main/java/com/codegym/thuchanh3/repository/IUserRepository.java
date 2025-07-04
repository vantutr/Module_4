package com.codegym.thuchanh3.repository;

import com.codegym.thuchanh3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
