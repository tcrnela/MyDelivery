package com.tcrnela.delivery.controller;

import com.tcrnela.delivery.dto.LoginRequestDto;
import com.tcrnela.delivery.dto.LoginResponseDto;
import com.tcrnela.delivery.dto.SignupRequestDto;
import com.tcrnela.delivery.dto.SignupResponseDto;
import com.tcrnela.delivery.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 응답코드 필요
    @PostMapping
    public ResponseEntity<SignupResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        SignupResponseDto response = userService.signup(signupRequestDto);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        return null;
    }
}
