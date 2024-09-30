package com.mrahmed.HRandPayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "isActive")
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId")
    @JsonBackReference
    private Department department;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<AdvanceSalary> advanceSalaries;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Bonus> bonuses;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Leave> leaves;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Salary> salaries;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<PaymentReceipt> paymentReceipts;

}

