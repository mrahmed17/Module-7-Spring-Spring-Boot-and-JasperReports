package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "bonuses")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bonus {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id",
            nullable = false)
    private Employee employee;


    @Column(nullable = false, length =5)
    private double bonusAmount;

    @Column(nullable = false)
    private Date bonusDate;


}
