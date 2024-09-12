    package com.mrahmed.FirstSpringBoot.entity;

    import jakarta.persistence.*;
    import lombok.*;

    @Entity
    @Table(name = "departments")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Department {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false, unique = true, length = 40)
        private String name;

        @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(name = "facId")
        private Faculty faculty;
    }