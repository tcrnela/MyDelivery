package com.tcrnela.delivery.entity;

import com.tcrnela.delivery.dto.OrderRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_order")
@Getter
@NoArgsConstructor
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Order(OrderRequestDto orderRequestDto, Menu menu, User user) {
        this.quantity = orderRequestDto.getQuantity();
        this.menu = menu;
        this.address = orderRequestDto.getAddress();
        this.status = OrderStatusEnum.ORDERED;
        this.user = user;
        this.price = menu.getPrice() * this.quantity;
    }

    public void delete() {
        this.status = OrderStatusEnum.CANCELED;
    }
}
