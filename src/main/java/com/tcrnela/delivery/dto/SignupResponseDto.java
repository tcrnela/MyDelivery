package com.tcrnela.delivery.dto;

import com.tcrnela.delivery.entity.User;
import com.tcrnela.delivery.entity.UserRoleEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignupResponseDto {
    private String userid;
    private UserRoleEnum role;

    public SignupResponseDto (User user) {
        this.userid = user.getUserid();
        this.role = user.getRole();
    }
}
