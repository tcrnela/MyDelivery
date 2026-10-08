package com.tcrnela.delivery.repository;

import com.tcrnela.delivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 쿼리메소드로 아이디 중복 확인, 역할병 주문 목록 조회 생성 필요
    boolean existsUserByUserid(String name);
}
