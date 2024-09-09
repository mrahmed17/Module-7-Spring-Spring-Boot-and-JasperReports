package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "salaries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double medicare;
    private boolean overTime;
    private double fund;
    private double netSalary;
    private Date paymentDate;
    private double insurance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advancesalaryId")
    private AdvanceSalary advanceSalary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bonusId")
    private Bonus bonus;


}
