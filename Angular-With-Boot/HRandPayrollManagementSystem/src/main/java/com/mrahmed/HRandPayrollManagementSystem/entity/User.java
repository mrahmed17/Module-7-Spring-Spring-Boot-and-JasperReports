package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String fullName;
    private String email;
    private String password;
    private String address;
    private String gender;
    private Date dateOfBirth;
    private String nationalId;
    private String contact;
    private String role;
    private double basicSalary;
    private Date joinedDate;
    private boolean isActive;
    private Date createdAt;
    private Date updateAt;
    private String profilePhoto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

}
