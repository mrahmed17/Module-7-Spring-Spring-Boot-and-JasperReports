package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "salaries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int medicare;
    private boolean overTime; //If yes then add =(baseSalary/4*5)
    private int fund; // baseSalary * (2% = 0.02)
    private int netSalary; // (base-advance+bonus+insurance+medicare+overTime-fund)
    private Date paymentDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advancesalary_id")
    private AdvanceSalary advanceSalary; // advancedSalary
    private int insurance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bonus_id", nullable = false)
    private Bonus bonus; // bonusAmount



}
