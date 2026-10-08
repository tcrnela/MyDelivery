package com.tcrnela.delivery.dto;

import com.tcrnela.delivery.entity.Menu;
import com.tcrnela.delivery.entity.Order;
import com.tcrnela.delivery.entity.OrderStatusEnum;
import com.tcrnela.delivery.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class OrderResponseDto {
//    private Long menuId;
    private Long quantity;
    private String address;
    private Long price;
    private OrderStatusEnum status;
//    private Menu menu;
//    private User user;

    public OrderResponseDto(Order order) {
//        this.menuId = order.getMenu().getId();
        this.quantity = order.getQuantity();
        this.address = order.getAddress();
        this.price = order.getPrice();
        this.status = order.getStatus();
//        this.menu = order.getMenu();
//        this.user = order.getUser();
    }

}
