package com.mrahmed.HRandPayrollManagementSystem.entity;



import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "branches")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updateAt;

    @Column(nullable = false)
    private String photo;
}
