package com.mrahmed.HRandPayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
    private LocalDate dateOfBirth;
    private String nationalId;
    private String contact;
    private BigDecimal basicSalary;
    private LocalDate joinedDate;
    private String profilePhoto;

    @Column(name = "isActive", nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @UpdateTimestamp
    private LocalDate updatedAt;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "companyId")
//    private Company company;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "departmentId")
//    private Department department;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "branchId")
//    private Branch branch;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Attendance> attendances;


}
