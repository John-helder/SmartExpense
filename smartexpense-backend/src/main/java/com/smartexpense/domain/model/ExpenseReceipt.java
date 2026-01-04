package com.smartexpense.domain.model;

import com.smartexpense.domain.enums.ExpenseReceiptStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.logging.Level;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "expense_receipts")
public class ExpenseReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String filePath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseReceiptStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public ExpenseReceipt (User user, String filePath) {
        this.user = user;
        this.filePath = filePath;
        this.status = ExpenseReceiptStatus.PROCESSING;
        this.createdAt = LocalDateTime.now();
    }
    @PrePersist
    private void prePersist(){

        this.createdAt = LocalDateTime.now();
    }

    public void updateStatus(ExpenseReceiptStatus newStatus){

        this.status = newStatus;
    }
}
