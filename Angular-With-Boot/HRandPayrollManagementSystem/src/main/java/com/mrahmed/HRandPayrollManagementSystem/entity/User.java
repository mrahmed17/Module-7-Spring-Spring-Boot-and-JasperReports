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
    private Date dateOfBirth; //java.time.LocalDate
    private String nationalId;
    private String contact;
    private double basicSalary;
    private Date joinedDate; //java.time.LocalDate
    private boolean isActive;
    private Date createdAt; //java.time.LocalDate
    private Date updateAt; //java.time.LocalDate
    private String profilePhoto;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId")
    private Department department;


//    @Version
//    @NotNull
//    private Long version;


//    @PrePersist
//    public void validateIsActive() {
//        if (isActive != true && isActive != false) {
//            throw new RuntimeException("isActive must be either true or false");
//        }
//    }


//    @PrePersist
//    public void validateRole() {
//        if (role == null) {
//            throw new RuntimeException("Role cannot be null");
//        }
//    }


}
