package com.mrahmed.HRandPayrollManagementSystem.entity;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//
//@Entity
//@Table(name = "Companies")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Company {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String companyName;
//    private String photo;
//
//    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Branch> branches;
//
//    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Department> departments;
//
//    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private List<User> users;
//
//}

