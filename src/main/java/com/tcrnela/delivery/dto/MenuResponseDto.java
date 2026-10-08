package com.tcrnela.delivery.dto;

import com.tcrnela.delivery.entity.Menu;
import com.tcrnela.delivery.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class MenuResponseDto {

    private String name;
    private Long price;
    private String description;

    public MenuResponseDto(Menu menu) {
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.description = menu.getDescription();
    }
}
