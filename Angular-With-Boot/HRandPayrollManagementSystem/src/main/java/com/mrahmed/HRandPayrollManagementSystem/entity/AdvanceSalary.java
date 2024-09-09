package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "advanceSalaries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvanceSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double advanceSalary;
    private String reason;
    private Date advanceDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;


}
