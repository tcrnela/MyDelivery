package com.tcrnela.delivery.dto;

import com.tcrnela.delivery.entity.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotBlank
    @Size(min = 4, max = 20)
    private String userid;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotNull
    private UserRoleEnum role;
}
