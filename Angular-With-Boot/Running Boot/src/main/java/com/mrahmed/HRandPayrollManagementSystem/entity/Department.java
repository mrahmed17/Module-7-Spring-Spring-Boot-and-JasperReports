package com.mrahmed.HRandPayrollManagementSystem.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//@Entity
//@Table(name = "departments")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Department {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private String departmentName;
//    private int numOfEmployees;
//    private String photo;
//
//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "branch_id")
//    private Branch branch;
//
//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "company_id")
//    private Company company;
//
//    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
//    @JsonManagedReference
//    private List<User> users;
//
//}
