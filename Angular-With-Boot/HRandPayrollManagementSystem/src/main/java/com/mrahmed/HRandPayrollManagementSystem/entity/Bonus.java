package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "bonuses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bonus {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double bonusAmount;
    private Date bonusDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
