package com.tcrnela.delivery.controller;

import com.tcrnela.delivery.dto.SignupRequestDto;
import com.tcrnela.delivery.dto.SignupResponseDto;
import com.tcrnela.delivery.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 응답코드 필요
    @PostMapping("/signup")
    public SignupResponseDto signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }
}
