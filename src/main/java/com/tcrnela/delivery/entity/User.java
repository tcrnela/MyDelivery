package com.tcrnela.delivery.entity;

import com.tcrnela.delivery.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_user")
@Getter
@NoArgsConstructor
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false)
    private String password;

    // Email.. etc

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

//    public User(Long id, String userid, String password, UserRoleEnum role) {
//        this.id = id;
//        this.userid = userid;
//        this.password = password;
//        this.role = role;
//    }

    public User(SignupRequestDto signupRequestDto, String encodedPassword) {
        this.userid = signupRequestDto.getUserid();
        this.password = encodedPassword;
        this.role = signupRequestDto.getRole();
    }
}
