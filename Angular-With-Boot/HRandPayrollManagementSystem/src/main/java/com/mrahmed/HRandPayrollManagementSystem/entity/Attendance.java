package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "attendances")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false,
            referencedColumnName = "id")
    private Employee employee;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Date clockInTime;

    @Column(nullable = false)
    private Date clockOutTime;



}
