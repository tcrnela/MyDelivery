package com.tcrnela.delivery.repository;

import com.tcrnela.delivery.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
