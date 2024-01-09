package com.nobroker.nobroker.repository;

import com.nobroker.nobroker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
