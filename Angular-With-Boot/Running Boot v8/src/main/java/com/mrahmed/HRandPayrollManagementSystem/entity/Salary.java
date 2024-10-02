package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "salaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime paymentDate;
    private double medicare;
    private double providentFund; // baseSalary * (2% = 0.02)
    private double insurance;
    private double transportAllowance;
    private double telephoneSubsidy;
    private double utilityAllowance;
    private double domesticAllowance;
    private double lunchAllowance;
    private double netSalary;
    private double tax;
    @Enumerated(EnumType.STRING)
    private Month paymentMonth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;



}

