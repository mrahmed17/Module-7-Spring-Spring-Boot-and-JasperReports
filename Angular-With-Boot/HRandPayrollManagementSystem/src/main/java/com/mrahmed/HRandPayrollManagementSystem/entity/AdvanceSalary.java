package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "advance_salaries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvanceSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double advanaceSalary;
    private String reason;
    private Date advanceDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;



}
