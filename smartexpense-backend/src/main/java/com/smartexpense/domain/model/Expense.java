package com.smartexpense.domain.model;

import com.smartexpense.domain.enums.ExpenseStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id")
    private Company company;


    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate expenseDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Expense(User user, Company company, BigDecimal amount, LocalDate expenseDate) {
        this.user = user;
        this.company = company;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.status = ExpenseStatus.UNDER_REVIEW;
    }
    @PrePersist
    private void prePersist(){
        this.createdAt = LocalDateTime.now();
    }
}
