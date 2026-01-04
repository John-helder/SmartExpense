package com.smartexpense.domain.model;

import com.smartexpense.domain.enums.ConfidenceScore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "extracted_data")
public class ExtractedData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "expense_receipt_id")
    private ExpenseReceipt expenseReceipt;

    @Column(nullable = false, length = 14)
    private String issuerCnpj;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConfidenceScore confidenceScore;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public ExtractedData (ExpenseReceipt expenseReceipt,
                          String issuerCnpj,
                          BigDecimal totalAmount,
                          LocalDate issueDate
    ) {
        this.expenseReceipt = expenseReceipt;
        this.issuerCnpj = issuerCnpj;
        this.totalAmount = totalAmount;
        this.issueDate = issueDate;
        this.confidenceScore = ConfidenceScore.LOW;
    }

    @PrePersist
    private void prePersist () {
        this.createdAt = LocalDateTime.now();
    }

    private void updateConfidenceStatus (ConfidenceScore newScore) {
        this.confidenceScore = newScore;
    }

}