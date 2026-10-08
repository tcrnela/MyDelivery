package com.tcrnela.delivery.service;

import com.tcrnela.delivery.dto.MenuRequestDto;
import com.tcrnela.delivery.dto.MenuResponseDto;
import com.tcrnela.delivery.entity.Menu;
import com.tcrnela.delivery.entity.User;
import com.tcrnela.delivery.repository.MenuRepository;
import com.tcrnela.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public void create(MenuRequestDto menuRequestDto, String userid) {
        User owner = userRepository.findByUserid(userid).get();

        Menu menu = new Menu(menuRequestDto, owner);
        menuRepository.save(menu);
    }
}
