package com.tcrnela.delivery.entity;

import com.tcrnela.delivery.dto.SignupRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

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

    public User(SignupRequestDto signupRequestDto) {
        this.userid = signupRequestDto.getUserid();
        this.password = signupRequestDto.getPassword();
        this.role = signupRequestDto.getRole();
    }
}
