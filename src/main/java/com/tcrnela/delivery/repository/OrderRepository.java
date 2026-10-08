package com.tcrnela.delivery.repository;

import com.tcrnela.delivery.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByMenu_Owner_Userid(String userid);

    List<Order> findByUser_Userid(String userid);
}
