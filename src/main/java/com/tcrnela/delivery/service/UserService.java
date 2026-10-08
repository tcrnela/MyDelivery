package com.tcrnela.delivery.service;

import com.tcrnela.delivery.dto.SignupRequestDto;
import com.tcrnela.delivery.dto.SignupResponseDto;
import com.tcrnela.delivery.entity.User;
import com.tcrnela.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        // ID 중복 검사
        if (userRepository.existsUserByUserid(signupRequestDto.getUserid())) {
            return null;
        }

        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = new User(signupRequestDto, encodedPassword);
        userRepository.save(user);
        SignupResponseDto signupResponseDto = new SignupResponseDto(user);

        return signupResponseDto;
    }
}
