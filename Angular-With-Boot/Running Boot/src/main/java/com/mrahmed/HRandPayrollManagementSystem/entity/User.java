package com.mrahmed.HRandPayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String email;
    private String password;
    private String address;
    private String gender;
    private LocalDate dateOfBirth;
    private String nationalId;
    private String contact;
    private double basicSalary;
    private LocalDate joinedDate;
    private String profilePhoto;

    @Column(name = "isActive")
    private boolean active = true;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId", nullable = false)
    @JsonBackReference
    private Department department;



}

