package com.tcrnela.delivery.controller;

import com.tcrnela.delivery.dto.MenuRequestDto;
import com.tcrnela.delivery.dto.MenuResponseDto;
import com.tcrnela.delivery.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    // 목록 조회
    @GetMapping
    public List<MenuResponseDto> findAll() {
        return menuService.findAll();
    }

    @GetMapping("/{id}")
    public MenuResponseDto find(@PathVariable Long id) {
        return menuService.find(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuResponseDto> update(@PathVariable Long id, @Valid @RequestBody MenuRequestDto menuRequestDto, Authentication authentication) {
        return ResponseEntity.ok(menuService.update(id, menuRequestDto, authentication.getName()));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody MenuRequestDto menuRequestDto, Authentication authentication) {
        menuService.create(menuRequestDto, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
