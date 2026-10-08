package com.tcrnela.delivery.service;

import com.tcrnela.delivery.dto.MenuRequestDto;
import com.tcrnela.delivery.dto.OrderRequestDto;
import com.tcrnela.delivery.dto.OrderResponseDto;
import com.tcrnela.delivery.entity.*;
import com.tcrnela.delivery.repository.MenuRepository;
import com.tcrnela.delivery.repository.OrderRepository;
import com.tcrnela.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public void order(OrderRequestDto orderRequestDto, String userid) {
        User user = userRepository.findByUserid(userid).orElseThrow();
        Long menuId = orderRequestDto.getMenuId();
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "메뉴 번호가 올바르지 않습니다."
                ));
        if (menu.isDeleted()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "삭제된 메뉴입니다."
            );
        }

        Order order = new Order(orderRequestDto, menu, user);
        orderRepository.save(order);
    }

    public List<OrderResponseDto> get(String userid) {
        List<Order> list = orderRepository.findAll();
        List<Order> orders;
        User user = userRepository.findByUserid(userid).orElseThrow();

        if (user.getRole() == UserRoleEnum.OWNER) {
            orders = orderRepository.findByMenu_Owner_Userid(userid);
        } else {
            orders = orderRepository.findByUser_Userid(userid);
        }

        return orders.stream()
                .map(OrderResponseDto::new)
                .toList();
    }

    @Transactional
    public void delete(Long id, String userid) {
        User user = userRepository.findByUserid(userid).orElseThrow();
        Order order = orderRepository.findById(id).orElseThrow();
        if (!order.getUser().equals(user)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "나의 주문이 아닙니다."
            );
        }
        if (!order.getStatus().equals(OrderStatusEnum.ORDERED)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "처리 완료된 건입니다."
            );
        }
        order.delete();
    }
}
