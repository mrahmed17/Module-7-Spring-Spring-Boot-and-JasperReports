package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String email;
    private String password;
    private String address;
    private String gender;
    private Date dateOfBirth;
    private String nationalId;
    private String contact;
    private double basicSalary;
    private Date joinedDate;
    private boolean isActive;
    private Date createdAt;
    private Date updateAt;
    private String profilePhoto;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId")
    private Department department;


}
