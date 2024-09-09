package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "salaries")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE) // Make the no-argument constructor private
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double medicare;
    private boolean overTime; //If yes then add =(baseSalary/4*5)
    private double fund; // baseSalary * (2% = 0.02)
    private double netSalary; // (base-advance+bonus+insurance+medicare+overTime-fund)
    private Date paymentDate; //ava.time.LocalDate
    private double insurance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advancesalaryId")
    private AdvanceSalary advanceSalary; // advancedSalary

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bonusId")
    private Bonus bonus; // bonusAmount


//    @Version
//    @NotNull
//    private Long version;

//    @PrePersist
//    public void validateOverTime() {
//        if (overTime && netSalary < 0) {
//            throw new RuntimeException("Net salary cannot be negative when over time is true");
//        }
//    }


//    @PrePersist
//    public void validateNetSalary() {
//        if (netSalary < 0) {
//            throw new RuntimeException("Net salary cannot be negative");
//        }
//    }


//    @PrePersist
//    public void validateFundAndInsurance() {
//        if (fund < 0 || insurance < 0) {
//            throw new RuntimeException("Fund and insurance cannot be negative");
//        }
//    }


}
