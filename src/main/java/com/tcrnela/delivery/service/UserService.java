package com.tcrnela.delivery.service;

import com.tcrnela.delivery.dto.LoginRequestDto;
import com.tcrnela.delivery.dto.LoginResponseDto;
import com.tcrnela.delivery.dto.SignupRequestDto;
import com.tcrnela.delivery.dto.SignupResponseDto;
import com.tcrnela.delivery.entity.User;
import com.tcrnela.delivery.jwt.JwtService;
import com.tcrnela.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        // orelsethrow ?
        User user = userRepository.findByUserid(loginRequestDto.getUserid())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "아이디 또는 비밀번호가 올바르지 않습니다."
                ));
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "아이디 또는 비밀번호가 올바르지 않습니다."
            );
        }

        String token = jwtService.createToken(user);

        return new LoginResponseDto(token, "Bearer", jwtService.getExpirationSeconds());
    }
}
