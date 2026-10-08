package com.tcrnela.delivery.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String accessToken;
    private String tokenType;
    private Long expiresIn;

    public LoginResponseDto(String accessToken, String tokenType, Long expiresIn) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }
}
