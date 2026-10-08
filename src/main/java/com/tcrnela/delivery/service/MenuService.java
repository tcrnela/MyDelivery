package com.tcrnela.delivery.service;

import com.tcrnela.delivery.dto.MenuRequestDto;
import com.tcrnela.delivery.dto.MenuResponseDto;
import com.tcrnela.delivery.entity.Menu;
import com.tcrnela.delivery.entity.User;
import com.tcrnela.delivery.repository.MenuRepository;
import com.tcrnela.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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

    public List<MenuResponseDto> findAll() {
        List<Menu> all = menuRepository.findAll();
        List<MenuResponseDto> list = new ArrayList<>();

        for (Menu menu : all) {
            if (menu.isDeleted()) { continue; }
            MenuResponseDto menuResponseDto = new MenuResponseDto(menu);
            list.add(menuResponseDto);
        }
        return list;
    }

    public MenuResponseDto find(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "존재하지 않는 메뉴입니다."
                ));
        if (menu.isDeleted()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "존재하지 않는 메뉴입니다.");
        }
        return new MenuResponseDto(menu);
    }

    @Transactional
    public MenuResponseDto update(Long id, MenuRequestDto menuRequestDto, String userid) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "존재하지 않는 메뉴입니다."
                ));
        if (menu.isDeleted()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "존재하지 않는 메뉴입니다.");
        }
        if (!menu.getOwner().getUserid().equals(userid)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "내가 생성한 메뉴가 아닙니다.");
        }

        menu.update(menuRequestDto);
        return new MenuResponseDto(menu);
    }

    @Transactional
    public MenuResponseDto delete(Long id, String userid) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "존재하지 않는 메뉴입니다."
                ));
        if (menu.isDeleted()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "존재하지 않는 메뉴입니다.");
        }
        if (!menu.getOwner().getUserid().equals(userid)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "내가 생성한 메뉴가 아닙니다.");
        }

        menu.delete();
        return new MenuResponseDto(menu);
    }
}
