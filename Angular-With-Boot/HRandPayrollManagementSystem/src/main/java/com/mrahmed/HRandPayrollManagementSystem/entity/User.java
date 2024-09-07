package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =100, nullable = false)
    private String fullName;

    @Column(length =100, nullable = false)
    private String email;

    @Column(length =100, nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(length = 10, nullable = false)
    private String gender;

    @Column(length = 12, nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false, length =16)
    private String nationalId;

    @Column(nullable = false, length = 15)
    private String contact;

    @Column( nullable = false)
    private String role;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updateAt;

    @Column(nullable = false)
    private String profilePhoto;

}
