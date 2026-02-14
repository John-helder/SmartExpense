package com.smartexpense.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Company(Expense expense,
                   String name,
                   String cnpj,
                   Boolean active
    ) {
        this.expense = expense;
        this.name = name;
        this.cnpj = cnpj;
        this.active = active;
    }

    @PrePersist
    private void prePersist () {
        this.createdAt = LocalDateTime.now();
    }
}