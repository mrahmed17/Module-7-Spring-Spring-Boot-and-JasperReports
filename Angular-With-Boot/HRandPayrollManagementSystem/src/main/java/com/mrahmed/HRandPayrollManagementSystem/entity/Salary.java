package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(nullable = false)
    private Long id;

    private Double bonus;

    private Double insurance;

    private Double medicare;

    @Column(nullable = false, length=5)
    private boolean overTime; //If yes then add =(baseSalary/4*5)

    private Double fund; // baseSalary * (2% = 0.02)

    @Column(nullable = false)
    private Double netSalary; // (base-advance+bonus+insurance+medicare+overTime-fund)

    @Column(nullable = false)
    private Date salaryDate; //salary of which month

    private Date paymentDate;

    private Double advancedSalary;

    private String reason;

    private Date advanceDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // basicSalary

/*    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advancesalary_id", nullable = false)
    private AdvanceSalary advanceSalary; // advancedSalary

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bonus_id", nullable = false)
    private Bonus bonus; // bonusAmount*/

}
