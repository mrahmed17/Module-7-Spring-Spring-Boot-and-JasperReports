package com.mrahmed.HRandPayrollManagementSystem.entity;



import jakarta.persistence.*;
import lombok.*;

import java.spl.Date;

@Entity
@Table(name = "branches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private String country;
    private Date createdAt;
    private Date updateAt;
    private String photo;

}
