package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
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

    private String name;  //@NotNull @NotEmpty @Size(min = 3, max = 255)
    private String address;
    private String city;
    private String zipCode;
    private String country;
    private Date createdAt;
    private Date updateAt;
    private String branchPhoto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
    private Company company;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private List<User> users;



}
