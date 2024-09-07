package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "advance_salaries")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvanceSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false,
            referencedColumnName = "id")
    private Employee employee;

    @Column(nullable = false, length =5)
    private double advanaceSalary;


    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Date advanceDate;




}
