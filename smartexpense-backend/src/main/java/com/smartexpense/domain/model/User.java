package com.smartexpense.domain.model;

import com.smartexpense.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public User(String nome, String email, UserRole role) {
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    @PrePersist
    private void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    public void updateRole(UserRole newRole) {
        this.role = newRole;
    }

}
