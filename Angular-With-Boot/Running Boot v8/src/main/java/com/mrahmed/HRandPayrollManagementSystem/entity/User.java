package com.mrahmed.HRandPayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDate;
import java.util.List;

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

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Attendance> attendances;

    @ManyToOne
    private Department department;

    @OneToMany
    @JsonIgnore
    private List<Feedback> feedbacks;

    @OneToMany
    @JsonIgnore
    private List<Salary> salaries;



    @OneToMany
    @JsonIgnore
    private List<Bonus> bonuses;

    @OneToMany
    @JsonIgnore
    private List<Leave> leaves;

    @OneToMany
    @JsonIgnore
    private List<AdvanceSalary> advanceSalaries;

}

