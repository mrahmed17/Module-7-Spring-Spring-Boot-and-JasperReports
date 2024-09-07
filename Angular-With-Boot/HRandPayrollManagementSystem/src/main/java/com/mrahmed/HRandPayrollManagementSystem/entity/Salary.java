package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "salaries")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false,
            referencedColumnName = "id")
    private Employee employee; // basicSalary

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advancesalary_id", nullable = false,
            referencedColumnName = "id")
    private AdvanceSalary advanceSalary; // advancedSalary


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bonus_id", nullable = false,
            referencedColumnName = "id")
    private Bonus bonus; // bonusAmount


    @Column(nullable = false, length=5)
    private int insurance;

    @Column(nullable = false, length=5)
    private int medicare;

    @Column(nullable = false, length=5)
    private boolean overTime; //If yes then add =(baseSalary/4*5)

    @Column(nullable = false, length=5)
    private int fund; // baseSalary * (2% = 0.02)

    @Column(nullable = false)
    private int netSalary; // (base-advance+bonus+insurance+medicare+overTime-fund)

    @Column(nullable = false)
    private Date paymentDate;


}
