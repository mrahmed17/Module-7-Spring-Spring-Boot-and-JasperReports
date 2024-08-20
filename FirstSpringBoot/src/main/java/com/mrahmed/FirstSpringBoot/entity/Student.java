package com.mrahmed.FirstSpringBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//If you want to use all function on lombok, you can add lombok.(dot)*(star)

import java.util.Date;

@Entity
@Table(name="students")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 40, name = "studnetName")
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String cell;
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date dob; //java.sql.Date



}
