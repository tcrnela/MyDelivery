package com.tcrnela.delivery.entity;

import com.tcrnela.delivery.dto.MenuRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "menu")
@NoArgsConstructor
public class Menu extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    public Menu(MenuRequestDto menuRequestDto, User owner) {
        this.name = menuRequestDto.getName();
        this.price = menuRequestDto.getPrice();
        this.description = menuRequestDto.getDescription();
        this.deleted = false;
        this.owner = owner;
    }

    public void update(MenuRequestDto menuRequestDto) {
        this.name = menuRequestDto.getName();
        this.price = menuRequestDto.getPrice();
        this.description = menuRequestDto.getDescription();
    }
}
