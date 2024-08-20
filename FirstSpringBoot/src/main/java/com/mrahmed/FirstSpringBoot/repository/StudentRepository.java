package com.mrahmed.FirstSpringBoot.repository;

import com.mrahmed.FirstSpringBoot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends JpaRepository<Student, Integer> {




}
