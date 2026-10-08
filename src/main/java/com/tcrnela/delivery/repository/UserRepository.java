package com.tcrnela.delivery.repository;

import com.tcrnela.delivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
