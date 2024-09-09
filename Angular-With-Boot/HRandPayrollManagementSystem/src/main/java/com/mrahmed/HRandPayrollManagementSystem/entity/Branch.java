package com.mrahmed.HRandPayrollManagementSystem.entity;



import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "branches")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE) // Make the no-argument constructor private
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;  //@NotNull @NotEmpty @Size(min = 3, max = 255)
    private String address;
    private String city;
    private String zipCode;
    private String country;
    private Date createdAt; //java.time.LocalDateTime
    private Date updateAt; //java.time.LocalDateTime
    private String branchPhoto;


//    @Version
//    @NotNull
//    private Long version;

}
