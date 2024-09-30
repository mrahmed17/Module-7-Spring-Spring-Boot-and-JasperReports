package com.mrahmed.HRandPayrollManagementSystem.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "branches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String branchName;
    private String address;
    private String city;
    private String zipCode;
    private String country;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private String photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
    @JsonBackReference
    private Company company;

    @OneToMany(mappedBy = "branch")
    @JsonManagedReference
    private List<Department> departments;

}
