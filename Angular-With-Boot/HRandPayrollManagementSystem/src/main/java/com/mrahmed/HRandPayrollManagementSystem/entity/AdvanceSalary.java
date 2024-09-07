package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "advance_salaries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvanceSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false, length =5)
    private Double advanaceSalary;


    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Date advanceDate;




}
