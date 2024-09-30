package com.mrahmed.HRandPayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private double tax;
    private double netSalary;
    private int year;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "overTime")
    private List<Attendance> overTime; //Working hours are 8. If attendance checks over the 8 hours, it will count as overtime.
    //  Overtime salary calculation = (basicSalary from user divided 4 week * 5 days * 8 hours)

    @Enumerated(EnumType.STRING)
    private Month payrollMonth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advanceSalaryId")
    @JsonBackReference
    private AdvanceSalary advanceSalary;

    @OneToMany (fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "bonusId")
    private List<Bonus> bonuses;

    @OneToMany (fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "leaveId")
    private List<Leave> leaves;


}

