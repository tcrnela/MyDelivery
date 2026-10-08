package com.tcrnela.delivery.service;

import com.tcrnela.delivery.dto.MenuRequestDto;
import com.tcrnela.delivery.dto.OrderRequestDto;
import com.tcrnela.delivery.dto.OrderResponseDto;
import com.tcrnela.delivery.entity.Menu;
import com.tcrnela.delivery.entity.Order;
import com.tcrnela.delivery.entity.User;
import com.tcrnela.delivery.repository.MenuRepository;
import com.tcrnela.delivery.repository.OrderRepository;
import com.tcrnela.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}
