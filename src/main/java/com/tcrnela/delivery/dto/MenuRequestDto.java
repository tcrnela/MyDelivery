package com.tcrnela.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class MenuRequestDto {
    @NotBlank
    private String name;
    @NotNull
    @Positive
    private Long price;
    private String description;
    private boolean deleted = false;
}
