package com.tcrnela.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @NotBlank
    private String userid;

    @NotBlank
    private String password;
}
