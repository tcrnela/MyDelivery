package com.tcrnela.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class OrderRequestDto {
    @NotNull
    private Long menuId;

    @NotNull
    @Positive
    private Long quantity;

    @NotBlank
    private String address;
}
