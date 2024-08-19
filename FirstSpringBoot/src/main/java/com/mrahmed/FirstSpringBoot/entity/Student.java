package com.mrahmed.FirstSpringBoot.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="students")
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
    private Date dob;



}
