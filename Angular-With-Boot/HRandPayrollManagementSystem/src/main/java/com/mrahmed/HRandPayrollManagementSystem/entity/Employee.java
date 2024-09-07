package com.mrahmed.HRandPayrollManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "employees")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, length =100)
    private String fullName;

    @Column(nullable = false, length =100)
    private String email;

    @Column(nullable = false, length =100)
    private String password;

    @Column(nullable = false, length =255)
    private String address;

    @Column(nullable = false, length =10)
    private String gender;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false, length =16)
    private String nationalId;

    @Column(nullable = false, length =15)
    private String contact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable =false, name = "department_id",
            referencedColumnName = "id")
    private Department department;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private double basicSalary;

    @Column(nullable = false)
    private Date joinedDate;

    @Column(nullable = true)
    private boolean isActive;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updateAt;

    @Column(nullable = false)
    private String profilePhoto;


}
