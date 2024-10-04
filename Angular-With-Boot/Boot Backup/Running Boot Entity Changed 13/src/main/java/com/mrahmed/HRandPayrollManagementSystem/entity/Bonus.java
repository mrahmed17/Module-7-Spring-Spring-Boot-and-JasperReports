package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bonuses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double bonusAmount;
    private LocalDateTime bonusDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    //ManyToMany

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salaryId")
    private Salary salary;


}
