package com.mrahmed.HRandPayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "paymentreceipts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime paymentDate;
    private double totalPaidAmount;
    private double deductions;
    private double netPaidAmount;
    private boolean status; // Payment status is Paid or Unpaid

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paymentPayer")
    @JsonBackReference
    private User paymentPayer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paymentReceiverId")
    @JsonBackReference
    private User paymentReceiver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salaryId")
    @JsonBackReference
    private Salary salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advanceSalaryId")
    @JsonBackReference
    private AdvanceSalary advanceSalary; // Advance salary included in the receipt

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bonusId")
    @JsonBackReference
    private Bonus bonuses; // Bonuses included in the receipt



}
