package com.tcrnela.delivery.service;

import com.tcrnela.delivery.dto.SignupRequestDto;
import com.tcrnela.delivery.dto.SignupResponseDto;
import com.tcrnela.delivery.entity.User;
import com.tcrnela.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        User user = new User(signupRequestDto);
        // ID 중복 검사
        if (userRepository.existsUserByUserid(user.getUserid())) {
            return null;
        }
        userRepository.save(user);
        SignupResponseDto signupResponseDto = new SignupResponseDto(user);

        return signupResponseDto;
    }
}
