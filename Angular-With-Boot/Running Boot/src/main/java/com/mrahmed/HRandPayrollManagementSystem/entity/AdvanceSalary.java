package com.mrahmed.HRandPayrollManagementSystem.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "advancesalaries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvanceSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double advanceSalary;
    private String reason;
    private LocalDateTime advanceDate;
    private int year;

    @Enumerated(EnumType.STRING)
    private Month advanceMonth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
