package com.tcrnela.delivery.controller;

import com.tcrnela.delivery.dto.OrderRequestDto;
import com.tcrnela.delivery.dto.OrderResponseDto;
import com.tcrnela.delivery.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> order(@Valid @RequestBody OrderRequestDto orderRequestDto, Authentication authentication) {
        orderService.order(orderRequestDto, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<OrderResponseDto> get(Authentication authentication) {
        return orderService.get(authentication.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Authentication authentication) {
        orderService.delete(id, authentication.getName());
    }

    @PutMapping
    public ResponseEntity<Void> update() {
        return null;
    }
}
