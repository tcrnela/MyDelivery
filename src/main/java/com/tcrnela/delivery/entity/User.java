package com.tcrnela.delivery.entity;

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

    @Size(min = 4, max = 20)
    @Column(nullable = false, unique = true)
    private String userid;

    @Size(min = 8)
    @Column(nullable = false)
    private String password;

    // Email.. etc

    @Column(nullable = false)
    @Enumerated
    private UserRoleEnum role;

}
