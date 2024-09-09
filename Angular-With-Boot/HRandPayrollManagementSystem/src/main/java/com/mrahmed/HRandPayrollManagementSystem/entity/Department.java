package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE) // Make the no-argument constructor private
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String numberOfEmployees;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId")
    private Branch branch;


//    @Version
//    @NotNull
//    private Long version;

//    @PrePersist
//    public void validateNumberOfEmployees() {
//        if (numberOfEmployees < 0) {
//            throw new RuntimeException("Number of employees cannot be negative");
//        }
//    }

}
