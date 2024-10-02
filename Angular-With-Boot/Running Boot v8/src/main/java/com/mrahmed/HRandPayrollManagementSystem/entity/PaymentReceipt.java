package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "paymentReceipts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String receiptNumber;
    private double amount;
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "salaryId")
    private Salary salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiverId")
    private User receivers;



}
